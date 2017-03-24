import javax.swing.ImageIcon;

public class Stack {
	
	private Queue queue = new Queue();
	private static int s = 0;
	private ImageIcon addIcon = new ImageIcon(getClass().getResource("/images/+.png"));
	private ImageIcon minusIcon = new ImageIcon(getClass().getResource("/images/-.png"));
	private ImageIcon mulIcon = new ImageIcon(getClass().getResource("/images/x.png"));
	private ImageIcon divIcon = new ImageIcon(getClass().getResource("/images/div.png"));
	private ImageIcon oppIcon = new ImageIcon(getClass().getResource("/images/(.png"));
	private ImageIcon clospIcon = new ImageIcon(getClass().getResource("/images/).png"));
	
	public boolean isEmpty(){
		return queue.isEmpty();
	}
	
	public void push(String input){
		
		if(input.equals("+"))
			CrazyCalculatorMain.sShots.stackBlocks[s++].setIcon(addIcon);
		if(input.equals("-"))
			CrazyCalculatorMain.sShots.stackBlocks[s++].setIcon(minusIcon);
		if(input.equals("*"))
			CrazyCalculatorMain.sShots.stackBlocks[s++].setIcon(mulIcon);
		if(input.equals("/"))
			CrazyCalculatorMain.sShots.stackBlocks[s++].setIcon(divIcon);
		if(input.equals("("))
			CrazyCalculatorMain.sShots.stackBlocks[s++].setIcon(oppIcon);
		if(input.equals(")"))
			CrazyCalculatorMain.sShots.stackBlocks[s++].setIcon(clospIcon);
		
		queue.enqueue(input);
	}
	
	public String pop(){
		 Queue temp = new Queue();
		 
		String str = "";
		
		for(int i = 0; i < queue.getSize()-1; i++){
			temp.enqueue(queue.dequeue());
		}
	
		str = queue.dequeue();
		
		for(int j = 0; j < temp.getSize(); j++){
			queue.enqueue(temp.dequeue());
		}
		
		CrazyCalculatorMain.sShots.stackBlocks[--s].setIcon(null);
		
		return str;
	}
	
	public String peek(){
		return queue.peek();
	}
		
	public void getPanel(SnapShots p){
	}
	
}
