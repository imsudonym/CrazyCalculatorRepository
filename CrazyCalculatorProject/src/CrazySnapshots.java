import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

public class CrazySnapshots extends JPanel{
		
	private String s;		
	
	public JTextArea queueBlocks, 
					  stackBlocks,
					  arrayBlocks,
					  linkListBlocks;					  
	
	public static JScrollPane qScroll, sScroll, aScroll, lScroll;
			
	private JLabel queueLabel = new JLabel("  Queue:", SwingConstants.CENTER);
	private JLabel stackLabel = new JLabel("  Stack:", SwingConstants.CENTER);	
	private JLabel arrayLabel = new JLabel("  Pseudo Array:", SwingConstants.CENTER);
	private JLabel linkListLabel = new JLabel("  Linked List:", SwingConstants.CENTER);	
	public JLabel readLabl = new JLabel("Character: ");	
	public JLabel parsedLabl = new JLabel("Parsed: ");
	
	public JLabel read = new JLabel();
	public JTextArea parsed = new JTextArea();
	private JScrollPane pScroll = new JScrollPane(parsed);
	
	
	private JPanel[] panels = new JPanel[4];
	private JPanel panel = new JPanel();	
	
	public CrazySnapshots(){

		setLayout(null);
		
		panel.setLayout(new GridLayout(4, 1 , 10 , 10));
		
		readLabl.setFont(new Font("Consolas", Font.BOLD, 16));
		readLabl.setBounds(45, 45, 100, 20);
		
		parsedLabl.setFont(new Font("Consolas", Font.BOLD, 16));
		parsedLabl.setBounds(45, 70, 100, 20);
		
		read.setFont(new Font("Consolas", Font.BOLD, 16));
		read.setBounds(155, 45, 100, 20);		
		
		parsed.setFont(new Font("Consolas", Font.BOLD, 16));
		pScroll.setBorder(null);
		pScroll.setBounds(155, 70, 500, 20);
		pScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		
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
		queueLabel.setFont(new Font("Consolas", Font.BOLD, 16));		
		stackLabel.setFont(new Font("Consolas", Font.BOLD, 16));		
		arrayLabel.setFont(new Font("Consolas", Font.BOLD, 16));
		linkListLabel.setFont(new Font("Consolas", Font.BOLD, 16));				
		
		stackLabel.setBounds(10, 92, 110, 40);
		queueLabel.setBounds(10, 125, 110, 40);		
		arrayLabel.setBounds(5, 162, 180, 40);
		linkListLabel.setBounds(5, 195, 180, 40);				
		
		panel.setBounds(151, 95, 600, 140);
		
		add(readLabl);
		add(parsedLabl);
		add(read);		
		add(pScroll);
		add(queueLabel);
		add(stackLabel);
		add(arrayLabel);
		add(linkListLabel);				
		add(panel);
		

	}
	
}