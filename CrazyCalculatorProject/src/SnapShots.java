import javax.swing.*;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

public class SnapShots extends JPanel{
		
	private String s;		
	
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
	
}