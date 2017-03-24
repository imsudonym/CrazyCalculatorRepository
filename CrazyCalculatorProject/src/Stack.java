
public class Stack {
	
	private Queue queue = new Queue();
	public static int s = 0;

	public boolean isEmpty(){
		return queue.isEmpty();
	}
	
	public void push(String input){
				
		displayStack(input);
		queue.enqueue(input);
	}
	
	public String pop(){ 
		String str = "";
		
		for(int i = 0; i < queue.getSize()-1; i++){
			queue.enqueue(queue.dequeue());
		}
	
		str = queue.dequeue();
		
		if(s > 0)
			CrazyCalculatorMain.sShots.stackBlocks[--s].setText("");
		
		return str;
	}
	
	public String peek(){
		return queue.peek();
	}
		
	private void displayStack(String input){
		
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
		
	}
	
}
