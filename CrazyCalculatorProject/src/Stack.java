
public class Stack {
	
	private Queue queue = new Queue();
	public static int s = 0;

	public boolean isEmpty(){
		return queue.isEmpty();
	}
	
	public void push(String input){
		
		if(input.equals("+"))
			CrazyCalculatorMain.sShots.stackBlocks[s++].setText("+");
		if(input.equals("-"))
			CrazyCalculatorMain.sShots.stackBlocks[s++].setText("-");
		if(input.equals("*"))
			CrazyCalculatorMain.sShots.stackBlocks[s++].setText("x");
		if(input.equals("/"))
			CrazyCalculatorMain.sShots.stackBlocks[s++].setText("/");
		if(input.equals("("))
			CrazyCalculatorMain.sShots.stackBlocks[s++].setText("(");
		if(input.equals(")"))
			CrazyCalculatorMain.sShots.stackBlocks[s++].setText(")");
		
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
		
		CrazyCalculatorMain.sShots.stackBlocks[s--].setText("");
		
		return str;
	}
	
	public String peek(){
		return queue.peek();
	}
		
	public void getPanel(CrazyGUI p){
	}
	
}
