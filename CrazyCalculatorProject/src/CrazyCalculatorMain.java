import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CrazyCalculatorMain extends JFrame{
	
	
	public static String userInput = " ";
	public static String[] token; 
	public static JLabel input, output;
	private JPanel numbersPane, operationsPane;
	
	//Buttons
	private JButton[] numPad = new JButton[12];	
	private JButton[] opPad = new JButton[8];
	
	private static final String[] string1 = {"0", ".", "DEL"}; 
	private String[] string2 = {"(", ")", "*", "/", "+", "-", "AC", "="};	
	
	public static ArrayList<String> postfix = new ArrayList<String>();	
	
	public static SnapShots sShots = new SnapShots();
	private JPanel panel = new JPanel();	
	
	public static Stack opStack = new Stack(); 
	
	public CrazyCalculatorMain(){
		super("Crazy Calculator");
		setLayout(null);
		
		input = new JLabel(" input");
		input.setOpaque(true);
		input.setBackground(Color.WHITE);
		input.setSize(320,40); 
		input.setLocation(20,20);
		input.setFont(new Font("Consolas", Font.BOLD, 14));		
		
		output = new JLabel("output ");
		output.setOpaque(true);
		output.setBackground(Color.WHITE);
		output.setSize(320, 40);
		output.setLocation(20,60);
		output.setHorizontalAlignment(JLabel.RIGHT);
		output.setFont(new Font("Consolas", Font.BOLD, 14));
		
		
		numbersPane = new JPanel();
		numbersPane.setSize(170,200);
		numbersPane.setLocation(20,110);
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
		operationsPane.setLocation(200,110);
		operationsPane.setLayout(new GridLayout(4, 2, 1, 1));
		for(int i = 0; i < string2.length; i++){
			opPad[i] = new JButton(string2[i]);
			opPad[i].addActionListener(handler);
			operationsPane.add(opPad[i]);
		}
		
		sShots.setSize(380,410);
		sShots.setLocation(381,0);
		
		add(input); add(numbersPane); add(operationsPane);
		add(output); add(sShots);
	}
	
	public class Handler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Object source = e.getSource();
			
				//	"del" button
			if(source == numPad[11]){
				if(userInput.length() > 1){
					userInput = userInput.substring(0, userInput.length()-2);
				}else if(userInput.length() == 1){
					userInput = userInput.substring(0, userInput.length()-1);
				}
							
				input.setText(userInput);
				
			}			
				//	"AC" button
			else if(source == opPad[6]){				
				userInput = " ";								
				input.setText(userInput);
				output.setText("");
				
				int i = postfix.size()-1;
				
				//	empty postfix array
				while(i >= 0){					
					postfix.remove(i);
					i--;
				}				
				
				PseudoArray.ctr = 0;
			}
			else if(source == opPad[7]){
								
				sShots.callRun();
				
			}			
			else{				
				
				//	number buttons 1-9
				for(int j = 0; j < 9; j++){
					if(source == numPad[j]){
						userInput += ""+(j+1);						
					}										
				}
				
				//	button "0"
				if(source == numPad[9]){
					userInput += "0";								
				}
				
				//	button "."
				if(source == numPad[10]){				
					if(userInput.length() > 0){
						if(Character.isDigit(userInput.toCharArray()[userInput.length()-1]))
							userInput += ".";
						else
							userInput += "0.";
					}else 						
						userInput += "0.";
				}				
				
				//	operations
				if(source == opPad[0]){										
					userInput += string2[0] + " ";
				}
									
				
				if(source == opPad[1]){					
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
	
	public JPanel getsShots(){
		return sShots;
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
	
	public static void main(String args[]){
		CrazyCalculatorMain frame = new CrazyCalculatorMain();
		
		frame.setSize(2*380+30,410);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}