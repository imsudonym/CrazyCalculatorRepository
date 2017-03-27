import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

public class CrazyGUI extends JPanel{
		
	private String s;		
	
	private JLabel queueLabel = new JLabel("  Queue:");
	private JLabel stackLabel = new JLabel("  Stack:");
	protected JLabel postfixLabel = new JLabel("  Postfix:");
	private JLabel arrayLabel = new JLabel("  Pseudo Array:");
	private JLabel linkListLabel = new JLabel("  Linked List:");
	private JLabel postfixEvaLabel = new JLabel("  Stack (Evaluation):");
	
	public  JLabel[] queueBlocks = new JLabel[30];
	public JLabel[] stackBlocks = new JLabel[30];
	public JLabel[] arrayBlocks = new JLabel[30];
	public JLabel[] linkListBlocks = new JLabel[30];
	public JLabel[] postfixEvaBlocks = new JLabel[20];
	
	private JPanel[] panels = new JPanel[6];
	private JPanel[] inPanels = new JPanel[5];
	
	public CrazyGUI(){

		setLayout(new GridLayout(6,1));
		
		// panels for queue, stacks, postfix, and final answer
		for(int i = 0; i < 6; i ++){
			panels[i] = new JPanel();
			panels[i].setLayout(new GridLayout(2,1));
			panels[i].setBackground(Color.GRAY);
			add(panels[i]);
		}				
		
		// labels for queue, stack, postfix, final answer
		queueLabel.setFont(new Font("Consolas", Font.BOLD, 14));
		stackLabel.setFont(new Font("Consolas", Font.BOLD, 14));
		postfixLabel.setFont(new Font("Consolas", Font.BOLD, 17));
		arrayLabel.setFont(new Font("Consolas", Font.BOLD, 14));
		linkListLabel.setFont(new Font("Consolas", Font.BOLD, 14));
		postfixEvaLabel.setFont(new Font("Consolas", Font.BOLD, 14));
		
		//	inner panels for blocks
		for(int i = 0; i < 5; i++){
			inPanels[i] = new JPanel();
			inPanels[i].setLayout(new GridLayout(1, 30));			
		}					
		
		for(int i = 0; i < 30; i++){
			queueBlocks[i] = new JLabel("", SwingConstants.CENTER);
			queueBlocks[i].setFont(new Font("Consolas", Font.BOLD, 38));
			queueBlocks[i].setBackground(Color.WHITE);
			queueBlocks[i].setOpaque(true);
			queueBlocks[i].setBorder(BorderFactory.createLineBorder(Color.black));
			queueBlocks[i].setSize(150,100);
			
			stackBlocks[i] = new JLabel("", SwingConstants.CENTER);
			stackBlocks[i].setFont(new Font("Consolas", Font.BOLD, 38));
			stackBlocks[i].setBackground(Color.WHITE);
			stackBlocks[i].setOpaque(true);
			stackBlocks[i].setBorder(BorderFactory.createLineBorder(Color.black));
			stackBlocks[i].setSize(150,100);

			
			arrayBlocks[i] = new JLabel("", SwingConstants.CENTER);
			arrayBlocks[i].setFont(new Font("Consolas", Font.BOLD, 38));
			arrayBlocks[i].setBackground(Color.WHITE);
			arrayBlocks[i].setOpaque(true);
			arrayBlocks[i].setBorder(BorderFactory.createLineBorder(Color.black));
			arrayBlocks[i].setSize(150,100);
			
			linkListBlocks[i] = new JLabel("", SwingConstants.CENTER);
			linkListBlocks[i].setFont(new Font("Consolas", Font.BOLD, 38));
			linkListBlocks[i].setBackground(Color.WHITE);
			linkListBlocks[i].setOpaque(true);
			linkListBlocks[i].setBorder(BorderFactory.createLineBorder(Color.black));
			linkListBlocks[i].setSize(150,100);
			
			
			
			inPanels[0].add(stackBlocks[i]);
			inPanels[1].add(queueBlocks[i]);			
			inPanels[2].add(arrayBlocks[i]);
			inPanels[3].add(linkListBlocks[i]);			
			
		}
		
		for(int i = 0; i < 20; i++){
			postfixEvaBlocks[i] = new JLabel("", SwingConstants.CENTER);
			postfixEvaBlocks[i].setFont(new Font("Consolas", Font.BOLD, 12));
			postfixEvaBlocks[i].setBackground(Color.WHITE);
			postfixEvaBlocks[i].setOpaque(true);
			postfixEvaBlocks[i].setBorder(BorderFactory.createLineBorder(Color.black));
			postfixEvaBlocks[i].setSize(150,100);
			inPanels[4].add(postfixEvaBlocks[i]);
		}
		
		queueLabel.setBounds(20, 20, 100, 40);
		stackLabel.setBounds(20, 20, 100, 40);
		arrayLabel.setBounds(20, 20, 100, 40);
		linkListLabel.setBounds(20, 20, 100, 40);
		postfixEvaLabel.setBounds(20, 20, 100, 40);
				
		panels[0].add(stackLabel);
		panels[1].add(queueLabel);
		panels[2].add(arrayLabel);
		panels[3].add(linkListLabel);
		panels[4].add(postfixLabel);
		panels[4].add(postfixEvaLabel);

		panels[0].add(inPanels[0]);
		panels[1].add(inPanels[1]);
		panels[2].add(inPanels[2]);
		panels[3].add(inPanels[3]);
		panels[5].add(inPanels[4]);
	}
	
}