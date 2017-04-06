import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

public class CrazySnapshots extends JPanel{
		
	private String s;		
	
	public static JTextArea queueBlocks, 
					  stackBlocks,
					  arrayBlocks,
					  linkListBlocks;					  
	
	public static JScrollPane qScroll, sScroll, aScroll, lScroll;
			
	private JLabel queueLabel = new JLabel("  Queue:", SwingConstants.CENTER);
	private JLabel stackLabel = new JLabel("  Stack:", SwingConstants.CENTER);	
	private JLabel arrayLabel = new JLabel("  Pseudo Array:", SwingConstants.CENTER);
	private JLabel linkListLabel = new JLabel("  Linked List:", SwingConstants.CENTER);
	
	private JPanel[] panels = new JPanel[4];
	private JPanel panel = new JPanel();	
	
	public CrazySnapshots(){

		setLayout(null);
		
		panel.setLayout(new GridLayout(4, 1 , 10 , 10));
		
		// panels for queue, stacks, postfix, and final answer
		for(int i = 0; i < 4; i ++){
			panels[i] = new JPanel();
			panels[i].setLayout(null);			
			panel.add(panels[i]);
		}						
		
		queueBlocks = new JTextArea();
		queueBlocks.setFont(new Font("Consolas", Font.BOLD, 20));
		queueBlocks.setEditable(false);
		qScroll = new JScrollPane(queueBlocks);		
		qScroll.setBounds(30, 0, 500, 90);
		qScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		qScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		
		stackBlocks = new JTextArea();
		stackBlocks.setFont(new Font("Consolas", Font.BOLD, 20));	
		stackBlocks.setEditable(false);
		sScroll = new JScrollPane(stackBlocks);
		sScroll.setBounds(30, 0, 500, 90);
		sScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		
		arrayBlocks = new JTextArea();
		arrayBlocks.setFont(new Font("Consolas", Font.BOLD, 20));
		arrayBlocks.setEditable(false);
		aScroll = new JScrollPane(arrayBlocks);
		aScroll.setBounds(30, 0, 500, 90);
		aScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		aScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		
		linkListBlocks = new JTextArea();
		linkListBlocks.setFont(new Font("Consolas", Font.BOLD, 20));
		linkListBlocks.setEditable(false);
		lScroll = new JScrollPane(linkListBlocks);		
		lScroll.setBounds(30, 0, 500, 90);
		lScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		lScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		
		panels[0].add(sScroll);
		panels[1].add(qScroll);			
		panels[2].add(aScroll);
		panels[3].add(lScroll);
		
		// labels for queue, stack, postfix, final answer			
		queueLabel.setFont(new Font("Consolas", Font.BOLD, 20));		
		stackLabel.setFont(new Font("Consolas", Font.BOLD, 20));		
		arrayLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		linkListLabel.setFont(new Font("Consolas", Font.BOLD, 20));				
		
		stackLabel.setBounds(10, 50, 110, 40);
		queueLabel.setBounds(10, 95, 110, 40);		
		arrayLabel.setBounds(5, 145, 180, 40);
		linkListLabel.setBounds(5, 190, 180, 40);				
		
		panel.setBounds(151, 50, 600, 180);
		
		add(queueLabel);
		add(stackLabel);
		add(arrayLabel);
		add(linkListLabel);				
		add(panel);
		

	}
	
}