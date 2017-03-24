import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CrazyCalculatorMain extends JFrame implements Runnable{
	
	private int sleepTime = 700;
	private static String string = "  Postfix:  ";
	public static String userInput = " ";
	public static String[] token; 
	public static JTextField input, output;
	private static JPanel numbersPane, operationsPane;	
	
	//Buttons
	private JButton[] numPad = new JButton[12];	
	private JButton[] opPad = new JButton[8];
	
	private static final String[] string1 = {"0", ".", "DEL"}; 
	private String[] string2 = {"(", ")", "*", "/", "+", "-", "AC", "="};	
	
	
	public static CrazyGUI sShots = new CrazyGUI();		
	public static Stack opStack = new Stack();	
	private static Stack postfixStack = new Stack();
	public static ArrayList<String> postfix = new ArrayList<String>();		
	
	public CrazyCalculatorMain(){
		super("Crazy Calculator");
		setLayout(null);
				
		init();			
		
		add(input); add(numbersPane); add(operationsPane);
		add(output); add(sShots);
	}
	
	private void init(){
		
		input = new JTextField(" input");
		input.setEditable(false);
		input.setOpaque(true);
		input.setBackground(Color.WHITE);
		input.setSize(320,40); 
		input.setLocation(20,20);
		input.setFont(new Font("Consolas", Font.BOLD, 14));
		
		JScrollBar scrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
		scrollBar.setSize(320,17); 
		scrollBar.setLocation(20,62);
		BoundedRangeModel brm = input.getHorizontalVisibility();
	    scrollBar.setModel(brm);
	    add(scrollBar);
		
		output = new JTextField("output ");
		output.setEditable(false);
		output.setOpaque(true);
		output.setBackground(Color.WHITE);
		output.setSize(320, 40);
		output.setLocation(20,80);
		output.setHorizontalAlignment(JLabel.RIGHT);
		output.setFont(new Font("Consolas", Font.BOLD, 14));
		
		
		numbersPane = new JPanel();
		numbersPane.setSize(170,200);
		numbersPane.setLocation(20,140);
		numbersPane.setLayout(new GridLayout(4,3, 1, 1));
		
		Handler handler = new Handler();
		
		for(int i = 8; i >= 0; i--){
			numPad[i] = new JButton("" + (i+1));			
			numPad[i].addActionListener(handler);
			numbersPane.add(numPad[i]);
		}
		
		int j = 9;
		for(int i = 0; i < string1.length; i++){
			numPad[j] = new JButton(string1[i]);
			numPad[j].addActionListener(handler);
			numbersPane.add(numPad[j++]);
		}
		
		operationsPane = new JPanel();
		operationsPane.setSize(140,200);
		operationsPane.setLocation(200,140);
		operationsPane.setLayout(new GridLayout(4, 2, 1, 1));
		for(int i = 0; i < string2.length; i++){
			opPad[i] = new JButton(string2[i]);
			opPad[i].addActionListener(handler);
			operationsPane.add(opPad[i]);
		}
		
		sShots.setSize(600,410);
		sShots.setLocation(381,0);
	}
	
	
	
	public static boolean isOperator(String data){
		String[] operators = {"*", "/", "+", "-"};
		
		for(int i = 0; i < operators.length; i++){
			if(data.equals(operators[i]))
				return true;
		}
		
		return false;
	}	
	
	public static void evaluatePostfix(ArrayList<String> postfix){			
						
		double value = 0;
		
		for(int i = 0; i < postfix.size(); i++){
			if(isOperator(postfix.get(i))){
				if(postfix.get(i).equals("+"))				
					value = Double.parseDouble(postfix.get(i-2)) + Double.parseDouble(postfix.get(i-1));
				if(postfix.get(i).equals("-"))
					value = Double.parseDouble(postfix.get(i-2)) - Double.parseDouble(postfix.get(i-1));
				if(postfix.get(i).equals("*"))
					value = Double.parseDouble(postfix.get(i-2)) * Double.parseDouble(postfix.get(i-1));
				if(postfix.get(i).equals("/"))
					value = Double.parseDouble(postfix.get(i-2)) / Double.parseDouble(postfix.get(i-1));
				
				postfix.add(i+1, "" + value);

				int index = i;
				for(int j = 2; j >= 0; j--){
					postfix.remove(index-j);
					index--;
				}
				
				i -= 3;
			}
		}
		
		output.setText("" + value + " ");		
	}
	/*
	private static void evaluate(String data, String operand1, String operand2){				
		
		double value = 0;
		
		if(data.equals("+"))
			value = Double.parseDouble(operand1) + Double.parseDouble(operand2);
		if(data.equals("-"))
			value = Double.parseDouble(operand1) - Double.parseDouble(operand2);
		if(data.equals("*"))
			value = Double.parseDouble(operand1) * Double.parseDouble(operand2);
		if(data.equals("/"))		
			value = Double.parseDouble(operand1) / Double.parseDouble(operand2);
		
		System.out.println("push: " + value);
		postfixStack.push(""+value);
		
	}
	*/

	public void run(){
		
		try{
			
			token = userInput.split(" ");							
			
			if(infixIsValid()){

				for(int i = 0; i < token.length; i++)
				{		

						if(token[i].equals("(")){	
							opStack.push(token[i]);	
							Thread.sleep(sleepTime);
						}
						
						else if(token[i].equals(")")){
							
							while(!opStack.isEmpty())
							{
								String data = "";
								data = opStack.pop();
								Thread.sleep(sleepTime);
								
								if(!data.equals("(")){									
									postfix.add(data);										
									postfixUpdate(data);
									Thread.sleep(sleepTime);
									
								}else break;							
							}							
						}
						
						else if(isOperator(token[i])){																
							
							if(opStack.isEmpty()){
								opStack.push(token[i]);
								Thread.sleep(sleepTime);
							}
							else{

								while(!opStack.isEmpty())
								{
									String data = "";
									data = opStack.pop();
									Thread.sleep(sleepTime);
									
									if(data.equals("(")){										
										opStack.push(data);
										Thread.sleep(sleepTime);
										break;
									}
									
									if(isOperator(data)){
										if(isLessThan(data, token[i])){
											opStack.push(data);
											Thread.sleep(sleepTime);
											break;
										}
										
										else if(isGreaterOrEqual(data, token[i])){
											postfix.add(data);
											postfixUpdate(data);
											
											Thread.sleep(sleepTime);
											break;
										}
										Thread.sleep(sleepTime);
									}		
									
									
								}
								
								opStack.push(token[i]);	
								Thread.sleep(sleepTime);
							}											
						}	
						
						else{																		
							postfix.add(token[i]);
							postfixUpdate(token[i]);
							
							Thread.sleep(sleepTime);
						}
				}	// end parse
								
				while(!opStack.isEmpty()){
					
					String data = opStack.pop();
					Thread.sleep(sleepTime);
					
					postfix.add(data);
					postfixUpdate(data);
					
					Thread.sleep(sleepTime);
				}										
				
				//EVALUATE POSTFIX				
				evaluatePostfix(postfix);
				
			}else{

				output.setText("Syntax error ");					
			}			
			
			Thread t = new Thread();
			t.start();
		}catch(InterruptedException e){}
	}
	
	
	public void start(){		
		Thread t = new Thread(this);
		t.start();

	}
	
	private boolean infixIsValid(){

		boolean parenthesisHasMatch;
		boolean noConsecutiveOp;
		boolean operandsComplete;
		
		//	ERROR TRAPPINGS before transforming to postfix
		ArrayList<Integer> indices = new ArrayList<Integer>();
		
		for(int k = 0 ; k < token.length; k++){
			if(token[k].equals("(")){	
				indices.add(k);
			}
		}
		
		boolean temp = true;
		
		for(int k = 0; k < token.length; k++){
			if(decPoint(token[k]) == false){
				temp = false;
				break;
			}
		}
		
		parenthesisHasMatch = parenthesisHasMatch(indices);		
		noConsecutiveOp = noConsecutiveOp();
		operandsComplete = operandsComplete();
		
		//System.out.println("parenthesisHasMatch: " + parenthesisHasMatch);
		//System.out.println("noConsecutiveOp: " + noConsecutiveOp);
		//System.out.println("operandsComplete: " + operandsComplete);
		
		if(parenthesisHasMatch && noConsecutiveOp && operandsComplete && temp == true)
			return true;
		return false;
		
	}	
	
	private boolean operandsComplete(){
				
		if(userInput.toCharArray()[userInput.length()-1] == ' ')
			return false;		
		
		return true; 
	}
	
	private boolean decPoint(String s){
		int num = 0, i = 0;
		boolean temp = true;
		
		while(i < s.length()){
			if(s.charAt(i) == '.'){
				num++;
			}
			
			if(num > 1){
				temp = false;
				break;
			}
			i++;
		}
		return temp;
	}
	
	private boolean noConsecutiveOp(){
		
		for(int i = 1; i < token.length; i++){			
			if(isOperator(token[i-1]) && isOperator(token[i]))				
				return false;
		}
		
		return true;
	}
	
	private boolean parenthesisHasMatch(ArrayList<Integer> indices){
								
		ArrayList<Integer> indicesOfMatch = new ArrayList<Integer>();		
		
		for(int i = token.length; i >= 0; i--){
			for(int j = 0; j < token.length; j++){
				if(token[j].equals(")")){
					if(!indicesOfMatch.contains(j))
						indicesOfMatch.add(j);
				}
					
			}
		}		
				
		if(indicesOfMatch.size() == indices.size())
			return true;
		
		return false;
	}
	
	private boolean isLessThan(String op1, String op2){
		
		if(op2.equals("*") || op2.equals("/")){
			if(op1.equals("+") || op1.equals("-"))
				return true;			
		}				
		
		return false;
	}
	
	private boolean isGreaterOrEqual(String op1, String op2){
		
		if(op1.equals("*") || op1.equals("/")){
			if(op2.equals("*") || op2.equals("/") || op2.equals("+") || op2.equals("-")){
				return true;
			}
						
		}else if(op1.equals("+") || op1.equals("-")){
			if(op2.equals("+") || op2.equals("-"))
				return true;			
		}
		
		return false;
	}	

	private void postfixUpdate(String str){
		string += str;		
		sShots.postfixLabel.setText(string);
	}
	
	public class Handler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Object source = e.getSource();
			
				//	"del" button
			if(source == numPad[11]){
				
				if(userInput.length() == 1){
					userInput = "";
				}				
				
				if(userInput.length() > 1){
					if(userInput.substring(userInput.length()-2).equals("( ") || userInput.substring(userInput.length() - 2).equals(" )")){
						userInput = userInput.substring(0, userInput.length() - 2);						
					}
					else if(userInput.toCharArray()[userInput.length()-1] == ' '){						
							userInput = userInput.substring(0,userInput.length()-3);
					}else{
						userInput = userInput.substring(0, userInput.length()-1);
					
					}
				}								
							
				input.setText(userInput);
				
			}			
				//	"AC" button
			else if(source == opPad[6]){				
				userInput = " ";								
				input.setText(userInput);
				output.setText("");
				
				int i = postfix.size()-1;
				
				while(i >= 0){					
					postfix.remove(i);
					i--;
				}				
				
				string = "  Postfix:  ";
				sShots.postfixLabel.setText(string);
				PseudoArray.ctr = 0;
				PseudoArray.i = 0;
				PseudoArray.dispInt = 0;
				Stack.s = 0;
				Queue.q = 0;
				
			}
			else if(source == opPad[7]){
								
				input.setText(userInput);
				output.setText("");
				
				int i = postfix.size()-1;				
				while(i >= 0){					
					postfix.remove(i);
					i--;
				}				
				
				string = "  Postfix:  ";
				sShots.postfixLabel.setText(string);
				PseudoArray.ctr = 0;
				PseudoArray.i = 0;
				PseudoArray.dispInt = 0;
				Stack.s = 0;
				Queue.q = 0;
				
				start();
				
			}			
			else{				
				
				for(int j = 0; j < 9; j++){
					if(source == numPad[j])
						userInput += "" + (j + 1);						
				}
				
				if(source == numPad[9])
					userInput += "0";												
				
				if(source == numPad[10]){				
					if(userInput.length() > 0){
						if(Character.isDigit(userInput.toCharArray()[userInput.length()-1]))
							userInput += ".";
						else
							userInput += "0.";
					}else 						
						userInput += "0.";
				}				
				
				if(source == opPad[0]){
					if(userInput.toCharArray()[userInput.length()-1] == ' ')
						userInput += string2[0] + " ";
					else
						userInput += " " + string2[0] + " ";
				}													
				
				if(source == opPad[1]){
					if(userInput.toCharArray()[userInput.length()-1] == ' ')
						userInput += string2[1] + " ";
					else
						userInput += " " + string2[1];
				}					
				
				for(int i = 2; i < opPad.length-1; i++){
										
					if(source == opPad[i]){
						
						if(userInput.toCharArray()[userInput.length()-1] == '.')
							userInput += "0";
						
						
						if(userInput.length()>0){
							if(userInput.toCharArray()[userInput.length()-1] == ' ')
								userInput += string2[i] + " ";
							else
								userInput += " " + string2[i] + " ";	
						}else 
							userInput += " " + string2[i] + " ";					
					}
				}
				
				input.setText(userInput);	
				output.setText("");				
			}
			
		}
	}
	
	public static void main(String args[]){
		CrazyCalculatorMain frame = new CrazyCalculatorMain();
		
		frame.setSize(1050,410);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}