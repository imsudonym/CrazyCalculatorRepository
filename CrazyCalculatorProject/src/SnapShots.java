import javax.swing.*;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

public class SnapShots extends JPanel implements Runnable{
		
	private String s;
	private int sleepTime = 500;
	Thread t = new Thread(this);
	
	private JLabel queueLabel = new JLabel("  Queue:");
	private JLabel stackLabel = new JLabel("  Stack:");
	private JLabel postfixLabel = new JLabel("  Postfix:");
	private JLabel finalLabel = new JLabel("  Final Answer:");
	
	public  JLabel[] queueBlocks = new JLabel[10];
	public JLabel[] stackBlocks = new JLabel[10];
	public JLabel postfix = new JLabel();
	
	private JLabel finalAnswer = new JLabel();
	
	private JPanel[] panels = new JPanel[4];
	private JPanel[] inPanels = new JPanel[3];
	
	private static int p = 0;
	private ImageIcon addIcon = new ImageIcon(getClass().getResource("/images/+.png"));
	private ImageIcon minusIcon = new ImageIcon(getClass().getResource("/images/-.png"));
	private ImageIcon mulIcon = new ImageIcon(getClass().getResource("/images/x.png"));
	private ImageIcon divIcon = new ImageIcon(getClass().getResource("/images/div.png"));
	private ImageIcon oppIcon = new ImageIcon(getClass().getResource("/images/(.png"));
	private ImageIcon clospIcon = new ImageIcon(getClass().getResource("/images/).png"));
	
	public SnapShots(){

		setLayout(new GridLayout(4,1));
		
		// panels for queue, stacks, postfix, and final answer
		for(int i = 0; i < 4; i ++){
			panels[i] = new JPanel();
			panels[i].setLayout(new GridLayout(2,1));
			panels[i].setBackground(Color.GRAY);
			add(panels[i]);
		}				
		
		// labels for queue, stack, postfix, final answer
		queueLabel.setFont(new Font("Consolas", Font.BOLD, 14));
		stackLabel.setFont(new Font("Consolas", Font.BOLD, 14));
		postfixLabel.setFont(new Font("Consolas", Font.BOLD, 14));
		finalLabel.setFont(new Font("Consolas", Font.BOLD, 14));
		
		//	inner panels for blocks
		for(int i = 0; i < 2; i++){
			inPanels[i] = new JPanel();
			inPanels[i].setLayout(new GridLayout(1, 10));			
		}		
		
		inPanels[2] = new JPanel();		
		
		for(int i = 0; i < 10; i++){
			queueBlocks[i] = new JLabel();
			queueBlocks[i].setBackground(Color.WHITE);
			queueBlocks[i].setOpaque(true);
			queueBlocks[i].setBorder(BorderFactory.createLineBorder(Color.black));
			queueBlocks[i].setSize(150,100);
			
			stackBlocks[i] = new JLabel();
			stackBlocks[i].setBackground(Color.WHITE);
			stackBlocks[i].setOpaque(true);
			stackBlocks[i].setBorder(BorderFactory.createLineBorder(Color.black));
			stackBlocks[i].setSize(150,100);
						
			postfix.setBackground(Color.WHITE);
			postfix.setOpaque(true);
			postfix.setBorder(BorderFactory.createLineBorder(Color.black));
			//postfix.setSize(150,100);
			
			inPanels[0].add(queueBlocks[i]);
			inPanels[1].add(stackBlocks[i]);
			inPanels[2].add(postfix);
		}
		
		queueLabel.setBounds(20, 20, 100, 40);
		stackLabel.setBounds(20, 20, 100, 40);
		postfixLabel.setBounds(20, 20, 100, 40);
		finalLabel.setBounds(20, 20, 150, 40);
		
		inPanels[0].setBounds(10, 70, 100, 40);
		
		panels[0].add(queueLabel);
		panels[1].add(stackLabel);
		panels[2].add(postfixLabel);
		panels[3].add(finalLabel);

		panels[0].add(inPanels[0]);
		panels[1].add(inPanels[1]);
		panels[2].add(inPanels[2]);
	}
	
	
	public void run(){
		
		try{
			
			CrazyCalculatorMain.token = CrazyCalculatorMain.userInput.split(" ");							
			
			if(infixIsValid()){
				
				//start parsing
				for(int i = 0; i < CrazyCalculatorMain.token.length; i++)
				{		
						// if token is open parenthesis
						if(CrazyCalculatorMain.token[i].equals("(")){	
							CrazyCalculatorMain.opStack.push(CrazyCalculatorMain.token[i]);	
							Thread.sleep(sleepTime);
						}
						
						// if token is close parenthesis
						else if(CrazyCalculatorMain.token[i].equals(")")){
							
							while(!CrazyCalculatorMain.opStack.isEmpty())
							{
								String data = "";
								data = CrazyCalculatorMain.opStack.pop();
								Thread.sleep(sleepTime);
								
								if(!data.equals("(")){									
									CrazyCalculatorMain.postfix.add(data);
									
									Thread.sleep(sleepTime);
								}else break;							
							}							
						}
						
						// if token is an operator
						else if(CrazyCalculatorMain.isOperator(CrazyCalculatorMain.token[i])){																
							
							if(CrazyCalculatorMain.opStack.isEmpty()){
								CrazyCalculatorMain.opStack.push(CrazyCalculatorMain.token[i]);
								Thread.sleep(sleepTime);
							}
							else{

								while(!CrazyCalculatorMain.opStack.isEmpty())
								{
									String data = "";
									data = CrazyCalculatorMain.opStack.pop();
									Thread.sleep(sleepTime);
									
									if(data.equals("(")){										
										CrazyCalculatorMain.opStack.push(data);
										Thread.sleep(sleepTime);
										break;
									}
									
									if(CrazyCalculatorMain.isOperator(data)){
										if(isLessThan(data, CrazyCalculatorMain.token[i])){
											CrazyCalculatorMain.opStack.push(data);
											Thread.sleep(sleepTime);
											break;
										}
										
										else if(isGreaterOrEqual(data, CrazyCalculatorMain.token[i])){
											CrazyCalculatorMain.postfix.add(data);
											
											Thread.sleep(sleepTime);
											break;
										}
									}									
								}
								
								CrazyCalculatorMain.opStack.push(CrazyCalculatorMain.token[i]);	
							}											
						}	
						
						// if token is an operand
						else{																		
							CrazyCalculatorMain.postfix.add(CrazyCalculatorMain.token[i]);
							
							Thread.sleep(sleepTime);
						}
				}	// end parse
				
				// no more items				
				while(!CrazyCalculatorMain.opStack.isEmpty()){	
					CrazyCalculatorMain.postfix.add(CrazyCalculatorMain.opStack.pop());
					
					Thread.sleep(sleepTime);
				}										
				
				//EVALUATE POSTFIX
				CrazyCalculatorMain.evaluatePostfix(CrazyCalculatorMain.postfix);
			}else{

				CrazyCalculatorMain.output.setText("Syntax error ");					
			}			
			
			t = new Thread();
		}catch(InterruptedException e){}
	}
	
	
	public void callRun(){		
		t.start();
		//area.append(s);
	}
	
	private boolean infixIsValid(){

		boolean parenthesisHasMatch;
		boolean noConsecutiveOp;
		boolean operandsComplete;
		
		//	ERROR TRAPPINGS before transforming to postfix
		ArrayList<Integer> indices = new ArrayList<Integer>();
		
		for(int k = 0 ; k < CrazyCalculatorMain.token.length; k++){
			if(CrazyCalculatorMain.token[k].equals("(")){	
				indices.add(k);
			}
		}
		
		
		parenthesisHasMatch = parenthesisHasMatch(indices);		
		noConsecutiveOp = noConsecutiveOp();
		operandsComplete = operandsComplete();
		
		//System.out.println("parenthesisHasMatch: " + parenthesisHasMatch);
		//System.out.println("noConsecutiveOp: " + noConsecutiveOp);
		//System.out.println("operandsComplete: " + operandsComplete);
		
		if(parenthesisHasMatch && noConsecutiveOp && operandsComplete)
			return true;
		return false;
		
	}	
	
	private boolean operandsComplete(){
				
		if(CrazyCalculatorMain.userInput.toCharArray()[CrazyCalculatorMain.userInput.length()-1] == ' ')
			return false;		
		
		return true; 
	}
	
	private boolean noConsecutiveOp(){
		
		for(int i = 1; i < CrazyCalculatorMain.token.length; i++){			
			if(CrazyCalculatorMain.isOperator(CrazyCalculatorMain.token[i-1]) && CrazyCalculatorMain.isOperator(CrazyCalculatorMain.token[i]))				
				return false;
		}
		
		return true;
	}
	
	private boolean parenthesisHasMatch(ArrayList<Integer> indices){
								
		ArrayList<Integer> indicesOfMatch = new ArrayList<Integer>();		
		
		for(int i = CrazyCalculatorMain.token.length; i >= 0; i--){
			for(int j = 0; j < CrazyCalculatorMain.token.length; j++){
				if(CrazyCalculatorMain.token[j].equals(")")){
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
}
