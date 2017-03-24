public class Queue {
	PseudoArray array = new PseudoArray(100);	
		
	public static int q = 0;	
	
	public boolean isEmpty(){
		return array.isEmpty();
	}
	
	public void enqueue(String input){
		
		for(int i = 0; i < array.getSize(); i++){
			CrazyCalculatorMain.sShots.queueBlocks[i].setText(CrazyCalculatorMain.sShots.queueBlocks[i+1].getText());
		}
		
		displayQueue(input);
		array.add(input);
	}
	
	public String dequeue(){
		
		for(int i = 0; i < array.getSize(); i++){
			CrazyCalculatorMain.sShots.queueBlocks[i].setText(CrazyCalculatorMain.sShots.queueBlocks[i+1].getText());
		}
		
		q--;		
		return array.remove();
	}
	
	public String peek(){
		return array.get(getSize()-1).getValue(); 
	}
	
	public int getSize(){
		return array.getSize();
	}
	
	private void displayQueue(String input){
		
		if(input.equals("+"))
			CrazyCalculatorMain.sShots.queueBlocks[q++].setText("+");
		if(input.equals("-"))
			CrazyCalculatorMain.sShots.queueBlocks[q++].setText("-");
		if(input.equals("*"))
			CrazyCalculatorMain.sShots.queueBlocks[q++].setText("x");
		if(input.equals("/"))
			CrazyCalculatorMain.sShots.queueBlocks[q++].setText("/");
		if(input.equals("("))
			CrazyCalculatorMain.sShots.queueBlocks[q++].setText("(");
		if(input.equals(")"))
			CrazyCalculatorMain.sShots.queueBlocks[q++].setText(")");
		
	}
	
}
