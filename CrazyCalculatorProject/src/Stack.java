
public class Stack {
	
	private Queue queue = new Queue();
	public static int s = 0;
	public static int var = 0;

	public boolean isEmpty(){
		return queue.isEmpty();
	}
	
	public void push(String input){
				
		//displayStack(input);
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
		
		/*
		if(s > 0)
			CrazyCalculator.sShots.stackBlocks[--s].setText("");
		*/
		return str;
				
	}
	
	public String peek(){
		return queue.peek();
	}
		
	private void displayStack(String input){
		/*
		if(input.equals("+"))
			CrazyCalculator.sShots.stackBlocks.setText("+");
		else if(input.equals("-"))
			CrazyCalculator.sShots.stackBlocks.setText("-");			
		else if(input.equals("*"))
			CrazyCalculator.sShots.stackBlocks[s++].setText("x");
		else if(input.equals("/"))
			CrazyCalculator.sShots.stackBlocks[s++].setText("/");
		else if(input.equals("("))
			CrazyCalculator.sShots.stackBlocks[s++].setText("(");
		else if(input.equals(")"))
			CrazyCalculator.sShots.stackBlocks[s++].setText(")");
		else
			CrazyCalculator.sShots.postfixEvaBlocks[var++].setText(input);
		*/
	}
	
}
