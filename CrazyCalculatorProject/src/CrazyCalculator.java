import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CrazyCalculator extends JFrame implements Runnable{
	
	private static int sleepTime = 600;
	private static String string = "  Postfix:  ";
	public static String userInput = " ";
	public static String[] token; 
	public static JTextField input, output;
	private static JPanel numbersPane, operationsPane;	
	
	//Buttons
	public static JButton[] numPad = new JButton[12];	
	public static JButton[] opPad = new JButton[8];
	
	public static final String[] string1 = {"0", ".", "DEL"}; 
	public static String[] string2 = {"(", ")", "*", "/", "+", "-", "AC", "="};	
	
	
	public static CrazyGUI sShots = new CrazyGUI();		
	public static Stack opStack = new Stack();	
	private static Stack postfixStack = new Stack();
	public static ArrayList<String> postfix = new ArrayList<String>();		
		
	private static String character = "", parsed = "", commitStr = "", stackContents = "";
	
	
	public CrazyCalculator(){
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
		input.setSize(320, 40);
		input.setLocation(20,62);
		input.setBackground(Color.WHITE);		
		input.setFont(new Font("Consolas", Font.BOLD, 14));
		
		JScrollBar scrollBar = new JScrollBar(JScrollBar.HORIZONTAL);
		scrollBar.setSize(320,17); 
		scrollBar.setLocation(20,103);
		BoundedRangeModel brm = input.getHorizontalVisibility();
	    scrollBar.setModel(brm);
	    add(scrollBar);
		
		output = new JTextField("output ");
		output.setEditable(false);
		output.setOpaque(true);
		output.setBackground(Color.WHITE);		
		output.setSize(320,40); 
		output.setLocation(20,20);
		output.setHorizontalAlignment(JLabel.RIGHT);
		output.setFont(new Font("Consolas", Font.BOLD, 14));
		
		
		numbersPane = new JPanel();
		numbersPane.setSize(170,200);
		numbersPane.setLocation(20,140);
		numbersPane.setLayout(new GridLayout(4,3, 1, 1));
		
		Handler handler = new Handler();
		
		for(int i = 8; i >= 0; i--){
			numPad[i] = new JButton("" + (i+1));	
			numPad[i].setFont(new Font("Consolas", Font.BOLD, 12));
			numPad[i].addActionListener(handler);
			numbersPane.add(numPad[i]);
		}
		
		int j = 9;
		for(int i = 0; i < string1.length; i++){
			numPad[j] = new JButton(string1[i]);
			numPad[j].setFont(new Font("Consolas", Font.BOLD, 12));
			numPad[j].addActionListener(handler);
			numbersPane.add(numPad[j++]);
		}
		
		operationsPane = new JPanel();
		operationsPane.setSize(140,200);
		operationsPane.setLocation(200,140);
		operationsPane.setLayout(new GridLayout(4, 2, 1, 1));
		for(int i = 0; i < string2.length; i++){
			opPad[i] = new JButton(string2[i]);
			opPad[i].setFont(new Font("Consolas", Font.BOLD, 12));
			opPad[i].addActionListener(handler);
			operationsPane.add(opPad[i]);
		}
		
		sShots.setSize(825,410);
		sShots.setLocation(381,0);
	}
	
	
	public class Handler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Object source = e.getSource();
			
			if(source == numPad[11])		//	'del' button click
				pushDel();				
			else if(source == opPad[6])		//	'AC' button	click	
				pushAC();							
			else if(source == opPad[7]){	//	'=' button click						
				pushEquals();				
				start();
												
			}			
			else{				
				
				// numbers 1-9
				for(int j = 0; j < 9; j++){
					if(source == numPad[j]){
						if(isOperator(""+userInput.toCharArray()[userInput.length()-1]))
							userInput += " " + (j + 1);
						else
							userInput += "" + (j + 1);
					}
												
				}
				
				//	number '0' click
				if(source == numPad[9])
					userInput += "0";												
				
				//	decimal point click
				if(source == numPad[10]){				
					if(userInput.length() > 0){
						if(Character.isDigit(userInput.toCharArray()[userInput.length()-1]))
							userInput += ".";
						else
							userInput += "0.";
					}else 						
						userInput += "0.";
				}				
				
				//	'(' click
				if(source == opPad[0]){
					if(userInput.toCharArray()[userInput.length()-1] == ' ')
						userInput += string2[0] + " ";
					else
						userInput += " " + string2[0] + " ";
				}													
				
				//	')' click
				if(source == opPad[1]){
					if(userInput.toCharArray()[userInput.length()-1] == ' ')
						userInput += string2[1] + " ";
					else
						userInput += " " + string2[1];
				}					
				
				//	any operation click
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

	
		public void run(){
		
		token = userInput.split(" ");							
		
		if(infixIsValid()){
			System.out.println("Converting Infix to Postfix...");
			System.out.println("Read\t\tParsed\t\t\tPostfix\t\t\tStack");

			for(int i = 0; i < token.length; i++)
			{		

					character = token[i];
					parsed += token[i];					
					
					if(token[i].equals("(")){							
						
						opStack.push(token[i]);	
						
						stackContents += token[i];
						
						makeThreadSleep();
					}
					
					else if(token[i].equals(")")){
						
						while(!opStack.isEmpty())
						{
							String data = "";
							data = opStack.pop();							
							//makeThreadSleep();
							
							if(!data.equals("(")){									
								postfix.add(data);										
								postfixUpdate(data);
								
								stackContents = stackContents.substring(0, stackContents.length()-1);
								commitStr += data;
								
								makeThreadSleep();
								
							}else{
								stackContents = stackContents.substring(0, stackContents.length()-1);
								break;							
							}
							
							
						}							
					}
					
					else if(isOperator(token[i])){																
						
						if(opStack.isEmpty()){
							opStack.push(token[i]);
							
							stackContents += token[i];
							
							makeThreadSleep();
						}
						else{

							while(!opStack.isEmpty())
							{
								String data = "";
								data = opStack.pop();								
								makeThreadSleep();
								
								if(data.equals("(")){										
									opStack.push(data);
									
									makeThreadSleep();
									break;
								}
								
								if(isOperator(data)){
									if(isLessThan(data, token[i])){
										opStack.push(data);										
										
										makeThreadSleep();
										break;
									}
									
									else if(isGreaterOrEqual(data, token[i])){
										postfix.add(data);
										postfixUpdate(data);
									
										stackContents = stackContents.substring(0, stackContents.length()-1);
										commitStr += data;
										
										makeThreadSleep();
										break;
									}
								}		
								
								
							}
							
							opStack.push(token[i]);	
							
							stackContents += token[i];
							
							makeThreadSleep();
						}											
					}	
					
					else{																		
						postfix.add(token[i]);
						postfixUpdate(token[i]);
						
						commitStr += token[i];
						
						makeThreadSleep();
					}

					System.out.printf("%-10s\t%-10s\t\t%-10s\t\t%-10s\n", character, parsed, commitStr, stackContents);					
						
			}	// end parse
							
			character = "End";
			
			while(!opStack.isEmpty()){
				
				String data = opStack.pop();
				makeThreadSleep();
				
				postfix.add(data);
				postfixUpdate(data);
				
				stackContents = stackContents.substring(0, stackContents.length()-1);
				commitStr += data;
				
				System.out.printf("%-10s\t%-10s\t\t%-10s\t\t%-10s\n", character, parsed, commitStr, stackContents);		
				
				makeThreadSleep();
			}										
							
			System.out.println("Postfix: " + commitStr + "\n");
			System.out.println("Evaluating postfix...\n");
			System.out.println("Character\tParsed\t\t\tEvaluate\t\tStack");
			character = "";
			parsed = "";
			commitStr = "";
			stackContents = "";
			
			evaluatePostfix(postfix);
			
		}else{
			
			output.setForeground(Color.RED);
			output.setText("Syntax error ");					
		}			
		
		Thread t = new Thread();
		t.start();
	}	
	public static void makeThreadSleep(){
		try{
			
			Thread.sleep(sleepTime);
			
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public void start(){		
		Thread t = new Thread(this);
		t.start();

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

			String answer;
			int ctr = 1;
			
			String data = postfix.get(ctr++);
			
			character = data;
			parsed += data;
			
			String operand1, operand2;
			
			while(true){
				
				if(isOperator(data)){					
					operand1 = postfixStack.pop();
					sShots.postfixEvaBlocks[Stack.var--].setText("");										
					
					operand2 = postfixStack.pop();					
					sShots.postfixEvaBlocks[Stack.var--].setText("");
										
					commitStr += operand2 + data + operand1;
					stackContents = stackContents.substring(0, stackContents.length()-2);
					
					evaluate(data, operand1, operand2);					
					
				}else{
															
					postfixStack.push(data);			
										
					commitStr = "";
					stackContents += data;
				}
									
				if(ctr == postfix.size())
					break;
				
				System.out.printf("%-10s\t%-10s\t\t%-10s\t\t%-10s\n", character, parsed, commitStr, stackContents);		
				
				data = postfix.get(ctr++);
				
				character = data;
				parsed += data;
				
			}		
			
			answer = postfixStack.pop();
						
			sShots.postfixEvaBlocks[Stack.var--].setText("");
			stackContents = stackContents.substring(0, stackContents.length()-1);
			System.out.printf("%-10s\t%-10s\t\t%-10s\t\t%-10s\n", character, parsed, commitStr, stackContents);	
			System.out.printf("Answer: " + answer);	
			
			if(answer.equals("Infinity") || answer.equals("NaN"))
				output.setText("Math Error ");
			else
				output.setText(answer + " ");
		}

	private static void evaluate(String data, String operand1, String operand2){				
		
		double value = 0;
		
		if(data.equals("+"))
			value = Double.parseDouble(operand2) + Double.parseDouble(operand1);
		if(data.equals("-"))
			value = Double.parseDouble(operand2) - Double.parseDouble(operand1);
		if(data.equals("*"))
			value = Double.parseDouble(operand2) * Double.parseDouble(operand1);
		if(data.equals("/"))		
			value = Double.parseDouble(operand2) / Double.parseDouble(operand1);
				
		postfixStack.push(""+value);
		stackContents += value;
		
	}	
	
	private boolean infixIsValid(){

		boolean parenthesisHasMatch;
		boolean noConsecutiveOp;
		boolean operandsComplete;
		boolean noDoubleDecimal;		
		
		//	ERROR TRAPPINGS before transforming to postfix
		ArrayList<Integer> indices = new ArrayList<Integer>();
		
		for(int k = 0 ; k < token.length; k++){
			if(token[k].equals("(")){	
				indices.add(k);
			}
		}
		
		parenthesisHasMatch = parenthesisHasMatch(indices);		
		noConsecutiveOp = noConsecutiveOp();
		operandsComplete = operandsComplete();		
		noDoubleDecimal = noDoubleDecimal();
		
		if(parenthesisHasMatch && noConsecutiveOp && operandsComplete && noDoubleDecimal == true)
			return true;
		return false;
		
	}	

	private boolean noDoubleDecimal(){		
		
		for(int k = 0; k < token.length; k++){
			if(decPoint(token[k]) == false){
				return false;				
			}
		}
		
		return true;
	}
	private boolean operandsComplete(){
					
		if(userInput.toCharArray()[userInput.length()-1] == ' ')
			return false;
		else if (isOperator(""+userInput.toCharArray()[1]))
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
	
	private void pushAC(){
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
		sShots.postfixEvaBlocks[0].setText("");
		Stack.s = 0;	
		Stack.var = 0;
		
		PseudoArray.ctr = 0; 
		PseudoArray.temp = 0; 
		PseudoArray.dispInt = 0;				
		
		Queue.dispInt = 0; 
		Queue.temp = 0;
		
		LinkedList.dispInt = 0; 
		LinkedList.ctr = 0;
		
		character = "";
		parsed = "";
		stackContents = "";
		commitStr = "";
		
		output.setForeground(Color.black);
	}
	
	private void pushDel(){
		
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
	
	private void pushEquals(){
		input.setText(userInput);
		output.setText("");
		output.setForeground(Color.BLACK);
		
		int i = postfix.size()-1;				
		while(i >= 0){					
			postfix.remove(i);
			i--;
		}				
		
		string = "  Postfix:  ";
		sShots.postfixLabel.setText(string);
		
		Stack.s = 0;	
		Stack.var = 0;

		PseudoArray.ctr = 0;	
		PseudoArray.temp = 0;	
		PseudoArray.dispInt = 0;				
		
		Queue.dispInt = 0;	
		Queue.temp = 0;
		
		LinkedList.dispInt = 0;	
		LinkedList.ctr = 0;
		
		character = "";
		parsed = "";
		stackContents = "";
		commitStr = "";
	}
	
		public static void main(String args[]){
		CrazyCalculator frame = new CrazyCalculator();
		
		frame.setSize(1250,420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		//frame.setResizable(false);
		
		Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setMaximumSize(DimMax);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}