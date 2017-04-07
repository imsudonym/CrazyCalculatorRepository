import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CrazyCalculator extends JFrame implements Runnable{
	
	public static boolean evaluatingPostfix = false;
	private static boolean toggleOperation = false;
	private static int sleepTime = 700;
		
	public static String userInput = "";
	public static String[] token; 
	private static String character = "", parsed = "", commitStr = "", stackContents = "";
	
	private static JScrollPane pScroll;
	private static JTextArea postfixEvaBlocks;
	public static JTextArea io;	
	private static JPanel numbersPane, operationsPane;
	private static JPanel calcPanel;
	
	
	private static final JLabel title1 = new JLabel("Convert Infix to Postfix");
	private static final JLabel title2 = new JLabel("Evaluate Postfix");
	private static JLabel postfixLabel = new JLabel("  Postfix:");
	public static JLabel[] numPad = new JLabel[12];	
	public static JLabel[] opPad = new JLabel[8];	
	
	public static final String[] string1 = {"0", ".", "DEL"}; 
	public static String[] string2 = {"(", ")", "*", "/", "+", "-", "AC", "="};	
	
		
	public static CrazySnapshots sShots1 = new CrazySnapshots();
	public static CrazySnapshots sShots2 = new CrazySnapshots();
	public static Stack opStack = new Stack();	
	public static Stack postfixStack = new Stack();
	
	public static ArrayList<String> postfix = new ArrayList<String>();				
		
	private String[] images = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "zero", "decimal", "delete",
								"open", "close", "times", "divide", "plus", "minus", "ac", "equals"};
	private String[] images_hover = {"one_hover", "two_hover", "three_hover", "four_hover", "five_hover", "six_hover", "seven_hover", 
			"eight_hover", "nine_hover", "zero_hover", "decimal_hover", "delete_hover", "open_hover", "close_hover", "times_hover", "divide_hover", "plus_hover", "minus_hover", "ac_hover", "equals_hover"};
	
	private ImageIcon[] icon = new ImageIcon[images.length];
	private ImageIcon[] icon_hover = new ImageIcon[images_hover.length];
	
	public CrazyCalculator(){
		super("Crazy Calculator");
		setLayout(null);
		this.getContentPane().setBackground(Color.DARK_GRAY);
				
		init();					
		add(calcPanel);

	}
	
	private void init(){
		
		calcPanel = new JPanel();
		calcPanel.setLayout(null);
		calcPanel.setOpaque(true);
		calcPanel.setBackground(Color.BLACK);
		calcPanel.setBounds(0, 0, 440, 460);
		
		io = new JTextArea();		
		io.setFont(new Font("Consolas", Font.BOLD, 36));
		io.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				io.setFocusable(true);
				
				if(io.getText().equals("Math Error"))
					io.setText("");
			}
		});
		io.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
												
				char c = e.getKeyChar();
				if(((c < 40 ) || (c > 57)) && (c != KeyEvent.VK_BACK_SPACE)){
					e.consume();
				}
				
				if(c == KeyEvent.VK_ENTER){
					
					pushEquals();						
					if(userInput.length() > 0){								
						start();
					}
				}			
			}
		});
				
		
		JScrollPane ioScroll = new JScrollPane(io);
		ioScroll.setBounds(20, 50, 400, 60);
		ioScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		ioScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		calcPanel.add(ioScroll);
		
		disableKeys(io.getInputMap());
        disableKeys(ioScroll.getInputMap(
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT));
		
		for(int i = 0; i < images.length; i++){
			icon[i] = new ImageIcon(getClass().getResource("crazy_images/" + images[i] + ".png"));
		}
		
		for(int i = 0; i < images_hover.length; i++){
			icon_hover[i] = new ImageIcon(getClass().getResource("crazy_images/" + images_hover[i] + ".png"));
		}
		
		numbersPane = new JPanel();
		numbersPane.setBackground(Color.black);
		numbersPane.setBounds(20, 140, 220, 300);		
		numbersPane.setLayout(new GridLayout(4, 3, 1, 1));
		
		Handler handler = new Handler();
		
		for(int i = 8; i >= 0; i--){
			numPad[i] = new JLabel(icon[i]);	
			numPad[i].addMouseListener(handler);
			numbersPane.add(numPad[i]);
		}
				
		for(int i = 9; i < 12; i++){
			numPad[i] = new JLabel(icon[i]);
			numPad[i].setFont(new Font("Consolas", Font.BOLD, 12));
			numPad[i].addMouseListener(handler);
			numbersPane.add(numPad[i]);
		}
		
		operationsPane = new JPanel();
		operationsPane.setBackground(Color.black);
		operationsPane.setBounds(260, 140, 160, 300);
		operationsPane.setLayout(new GridLayout(4, 2, 1, 1));
		
		int k = 0;
		for(int i = 12; i < images.length; i++){
			opPad[k] = new JLabel(icon[i]);
			opPad[k].setFont(new Font("Consolas", Font.BOLD, 12));
			opPad[k].addMouseListener(handler);
			operationsPane.add(opPad[k++]);
		}
		
				
		title1.setFont(new Font("Consolas", Font.BOLD, 16));
		title1.setBounds(290, 40, 250, 20);
		title2.setFont(new Font("Consolas", Font.BOLD, 16));
		title2.setBounds(350, 380, 250, 20);
		
		postfixLabel.setFont(new Font("Consolas", Font.BOLD, 16));
		postfixEvaBlocks = new JTextArea();
		postfixEvaBlocks.setFont(new Font("Consolas", Font.BOLD, 14));
		pScroll = new JScrollPane(postfixEvaBlocks);
						
		sShots1.setBounds(30, 30, 750, 300);
		postfixLabel.setBounds(150, 290, 100, 20);
		pScroll.setBounds(250, 280, 430, 35);
		sShots2.setBounds(30, 360, 750, 300);
		
		calcPanel.add(numbersPane);
		calcPanel.add(operationsPane);

	}
	
	private void disableKeys(InputMap inputMap) {
	  String[] keys = {"TAB", "ENTER"};
	  for (String key : keys) {
	     inputMap.put(KeyStroke.getKeyStroke(key), "none");
	  }
	  
	}
	  
	public class Handler extends MouseAdapter{
		
		public void mouseEntered(MouseEvent e){
			Object source = e.getSource();
						
			for(int i = 0; i < 12; i++){
				if(source == numPad[i]){
					numPad[i].setIcon(icon_hover[i]);
					break;
				}
			}
			
			int k = 0;
			for(int i = 12; i < images.length; i++){
				if(source == opPad[k]){
					opPad[k].setIcon(icon_hover[i]);
					break;
				}					
				k++;
			}
		}
		
		public void mouseExited(MouseEvent e){
			Object source = e.getSource();
			
			for(int i = 0; i < 12; i++){
				if(source == numPad[i]){
					numPad[i].setIcon(icon[i]);
					break;
				}
									
			}
			
			int k = 0;
			for(int i = 12; i < images.length; i++){
				if(source == opPad[k]){
					opPad[k].setIcon(icon[i]);
					break;
				}					
				k++;
			}
		}
		
		public void mouseClicked(MouseEvent e){		
			Object source = e.getSource();
			userInput = io.getText();
			
			if(source == numPad[11])		
				pushDel();				
			else if(source == opPad[6])		
				pushAC();							
			else if(source == opPad[7]){						
				pushEquals();				
				start();
										
			}			
			else{				
				// numbers 1-9
				for(int j = 0; j < 9; j++)
				{
					if(source == numPad[j])
					{
						userInput += (j + 1);
						
						toggleOperation = false;
						break;
					}																
				}
				
				//	number '0' click
				if(source == numPad[9]){
					
					userInput += "0";
					
					toggleOperation = false;
				}
				
				//	decimal point 
				if(source == numPad[10]){				
					if(userInput.length() > 0){
						if(Character.isDigit(userInput.toCharArray()[userInput.length()-1]))
							userInput += ".";
						else
							userInput += "0.";
					}else 						
						userInput += "0.";
					
					toggleOperation = false;
				}								
				//	open parenthesis
				if(source == opPad[0]){
										
					userInput += string2[0];
					
					toggleOperation = false;
				}																	
				//	close parenthesis
				if(source == opPad[1]){					
					
					userInput += string2[1];
					
					toggleOperation = false;
				}						
				//	toggleOperation indicates whether an operation button has been previously pressed
				if(toggleOperation == false){

					//	any operation click
					for(int i = 2; i < opPad.length-1; i++){
											
						if(source == opPad[i]){		
							
							toggleOperation = true;
							
							if(userInput.length() > 0){
								if(userInput.toCharArray()[userInput.length()-1] == '.')
									userInput += "0";																
								
								userInput += string2[i];
									
							}							
							break;
						}
					}
				}

				io.setText(userInput);					
			}			
		}
	}

	
		public void run(){
						
		//	Put spaces in between operators and operands
		String read = "";
		for(int i = 0; i < userInput.length(); i++){						
			
			if(isOperator("" + userInput.toCharArray()[i])){				

				if(isOperator("" + userInput.toCharArray()[i-1]) || userInput.toCharArray()[i-1] == '(' || userInput.toCharArray()[i-1] == ')')
					read += userInput.toCharArray()[i] + " ";	
				else 
					read += " " + userInput.toCharArray()[i] + " ";
				
			}else if(userInput.toCharArray()[i] == '(' || userInput.toCharArray()[i] == ')'){
				if(isOperator("" + userInput.toCharArray()[i-1]) || userInput.toCharArray()[i-1] == '(' || userInput.toCharArray()[i-1] == ')')
					read += userInput.toCharArray()[i] + " ";
				else 
					read += " " + userInput.toCharArray()[i] + " ";
			}				
			else
				read += userInput.toCharArray()[i];
		}		
		
		//	Split operators and operands
		token = read.split(" ");	
		
		if(infixIsValid()){
			
			io.setEditable(false);
			
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("\nConverting Infix to Postfix...");
			System.out.printf("%-20s\t\t%-20s\t\t%-20s\t\t%-20s\n", "Character", "Parsed", "Postfix", "Stack");

			for(int i = 0; i < token.length; i++)
			{		

					character = token[i];	
					parsed += token[i];	
					
					sShots1.read.setText(character);
					sShots1.parsed.setText(parsed);
					
					if(token[i].equals("(")){							
						
						opStack.push(token[i]);							
						stackContents += token[i];
						
						sShots1.stackBlocks.setText(stackContents);
						
						makeThreadSleep();
					}
					
					else if(token[i].equals(")")){
						
						while(!opStack.isEmpty())
						{
							String data = "";
							data = opStack.pop();							
							
							if(!data.equals("(")){									
								postfix.add(data);										
								
								//	@right most character of stackContents removed every loop 
								stackContents = stackContents.substring(0, stackContents.length()-1);								
								commitStr += data;
								
								sShots1.stackBlocks.setText(stackContents);
								postfixEvaBlocks.setText(commitStr);
								
								makeThreadSleep();
								
							}else{
								//	@right most character of stackContents removed every loop
								stackContents = stackContents.substring(0, stackContents.length()-1);
								sShots1.stackBlocks.setText(stackContents);
								break;							
							}
							
							
						}							
					}
					
					else if(isOperator(token[i])){																
						
						if(opStack.isEmpty()){
							opStack.push(token[i]);
							
							stackContents += token[i];
							sShots1.stackBlocks.setText(stackContents);
							
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
									
										stackContents = stackContents.substring(0, stackContents.length()-1);										
										commitStr += data;
										
										sShots1.stackBlocks.setText(stackContents);
										postfixEvaBlocks.setText(commitStr);
										
										makeThreadSleep();
										break;
									}
								}		
								
								
							}
							
							opStack.push(token[i]);	
							
							stackContents += token[i];
							sShots1.stackBlocks.setText(stackContents);
							
							makeThreadSleep();
						}											
					}	
					
					else{																		
						postfix.add(token[i]);
						
						commitStr += token[i];						
						postfixEvaBlocks.setText(commitStr);
						
						makeThreadSleep();
					}

					System.out.printf("%-20s\t\t%-20s\t\t%-20s\t\t%-20s\n", character, parsed, commitStr, stackContents);					
						
			}	// end parse
							
			character = "End";
			
			while(!opStack.isEmpty()){
				
				String data = opStack.pop();
				makeThreadSleep();
				
				postfix.add(data);
				
				if(stackContents.length() > 0)
					stackContents = stackContents.substring(0, stackContents.indexOf(data));
				commitStr += data;
				
				sShots1.stackBlocks.setText(stackContents);
				postfixEvaBlocks.setText(commitStr);
				
				System.out.printf("%-20s\t\t%-20s\t\t%-20s\t\t%-20s\n", character, parsed, commitStr, stackContents);		
				
				makeThreadSleep();
			}										
							
			System.out.println("Postfix: " + commitStr + "\n");
			System.out.println("Evaluating postfix...\n");
			System.out.printf("%-20s\t\t%-20s\t\t%-20s\t\t%-20s\n", "Character", "Parsed", "Evaluate", "Stack");
			character = "";
			parsed = "";
			commitStr = "";
			stackContents = "";
			
			evaluatingPostfix = true;
			
			evaluatePostfix(postfix);
			io.setEditable(true);
			
			evaluatingPostfix = false;
			
		}else{
			
			JOptionPane.showMessageDialog(null, "Syntax error!");
			io.setEditable(true);
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
			int ctr = 0;
			
			String data = postfix.get(ctr++);
			
			character = data;
			parsed += data;						
			
			String operand1, operand2;
			
			while(true){
				
				sShots2.read.setText(character);
				sShots2.parsed.setText(parsed);
				
				if(isOperator(data)){					
					operand1 = postfixStack.pop();								
					
					operand2 = postfixStack.pop();					
															
					commitStr = "";
					commitStr += operand2 + data + operand1;
					
					if(stackContents.length() > 0)
						stackContents = stackContents.substring(0, stackContents.indexOf(operand2));
					
					sShots2.stackBlocks.setText(stackContents);
					
					evaluate(data, operand1, operand2);					
					
				}else{
															
					postfixStack.push(data);			
										
					commitStr = "";
					stackContents += data;
					
					sShots2.stackBlocks.setText(stackContents);
				}

				System.out.printf("%-20s\t\t%-20s\t\t%-20s\t\t%-20s\n", character, parsed, commitStr, stackContents);						

				if(ctr == postfix.size()){
					character = "End";
					break;
				}
				
				data = postfix.get(ctr++);
				
				character = data;
				parsed += data;
				
			}		
			
			System.out.printf("%-20s\t\t%-20s\t\t%-20s\t\t%-20s\n", character, parsed, commitStr, stackContents);
			
			answer = postfixStack.pop();

			stackContents = stackContents.substring(0, stackContents.indexOf(answer));
			sShots2.stackBlocks.setText(stackContents);
			commitStr = "";
			
			System.out.printf("%-20s\t\t%-20s\t\t%-20s\t\t%-20s\n", character, parsed, commitStr, stackContents);	
			System.out.println("Answer: " + answer);	
			
			if(answer.equals("Infinity") || answer.equals("NaN")){
				io.setText("Math Error");
				io.setFocusable(false);
			}else
				io.setText(answer);
			
			System.out.println("--------------------------------------------------------------------------");
			userInput = "";
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
		
		sShots2.stackBlocks.setText(stackContents);
	}	
	
	private boolean infixIsValid(){

		ArrayList<Integer> indices = new ArrayList<Integer>();
		
		for(int k = 0 ; k < token.length; k++){
			if(token[k].equals("(")){	
				indices.add(k);
			}
		}
		
		if(parenthesisHasMatch(indices) && noConsecutiveOp() && operandsComplete() && noDoubleDecimal() && hasOpBeforeParenthesis())
			return true;
		return false;
		
	}	

	private boolean noDoubleDecimal(){		
		
		int ctr = 0;
		
		for(int k = 0; k < token.length; k++){
			for(int i = 0; i < token[k].length(); i++){
				if(token[k].toCharArray()[i] == '.'){
					ctr++;
				}				
			}	
			
			if(ctr > 1)
				return false;
		}
		
		return true;
	}
	
	private boolean operandsComplete(){
	
		if(userInput.length() > 0){
			if(isOperator("" + userInput.toCharArray()[0]))
				return false;
			else if (isOperator(""+userInput.toCharArray()[userInput.length()-1]))
				return false;				
		}			
		return true; 
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
	
	private boolean hasOpBeforeParenthesis(){
		
		boolean left = true, right = true;
		
		for(int i = 0 ; i < token.length; i++)
		{		
			if(token[i].equals("("))
			{
				if(i > 0)
				{									
					if(isOperator(token[i-1]))
						left = true;
					else 
						left = false;
							
				}else
					left = true;
				
			}
			
			if(token[i].equals(")"))
			{
				if(i < token.length-1)
				{					
					if(isOperator(token[i+1]))
						right = true;
					else 
						right = false;
																										
				}else
					right = true;					
				
			}			
		}
		
		if(left && right)
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
	
	private void pushAC(){
		userInput = "";								
		io.setText(userInput);		
		
		int i = postfix.size()-1;		
		while(i >= 0){					
			postfix.remove(i);
			i--;
		}				
		
		PseudoArray.ctr = 0; 		
		
		sShots1.read.setText("");
		sShots1.parsed.setText("");
		postfixEvaBlocks.setText("");		
		
		sShots2.read.setText("");
		sShots2.parsed.setText("");		
		
		Stack.stringTemp = "";
		Queue.stringTemp = "";
		PseudoArray.stringTemp = "";
		LinkedList.stringTemp = "";
		
		character = ""; parsed = ""; stackContents = ""; commitStr = "";
	}
	
	private void pushDel(){		
		userInput = io.getText();

		if(userInput.length() > 0){			
			userInput = userInput.substring(0, userInput.length()-1);
		}
					
		io.setText(userInput);
	}
	
	private void pushEquals(){
		userInput = io.getText();
		
		io.setText(userInput);
		
		int i = postfix.size()-1;				
		while(i >= 0){					
			postfix.remove(i);
			i--;
		}						
		
		PseudoArray.ctr = 0;	
		
		sShots1.read.setText("");
		sShots1.parsed.setText("");
		postfixEvaBlocks.setText("");		
		
		sShots2.read.setText("");
		sShots2.parsed.setText("");			
		
		Stack.stringTemp = "";
		Queue.stringTemp = "";
		PseudoArray.stringTemp = "";
		LinkedList.stringTemp = "";
		
		character = ""; parsed = ""; stackContents = ""; commitStr = "";
	}
	
		public static void main(String args[]){
		CrazyCalculator frame = new CrazyCalculator();			
		JFrame frame2 = new JFrame("Crazy Calculator");				
		
		frame2.setLayout(null);			
		frame2.getContentPane().setBackground(Color.DARK_GRAY);
		frame2.add(title1);
		frame2.add(title2);
		frame2.add(postfixLabel);
		frame2.add(pScroll);				
		frame2.add(sShots1);
		frame2.add(sShots2);
		
				
		frame2.setBounds(20, 50, 850, 750);
		frame2.setVisible(true);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(900, 100, 440, 480);
		frame.setVisible(true);
		frame.setResizable(false);

	}
}