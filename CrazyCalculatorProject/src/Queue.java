public class Queue{
	PseudoArray array = new PseudoArray(100);	
	public static int dispInt = 0;
	public static int temp = 0; 		
	
	public boolean isEmpty(){
		return array.isEmpty();
	}
	
	public void enqueue(String input){
				
		//displayQueue(input);
		CrazyCalculator.makeThreadSleep();		
		array.add(input);
	}
	
	public String dequeue(){
		/*
		if(temp < 20)
			CrazyCalculator.sShots.queueBlocks[temp++].setText("");
		*/
		if(temp == dispInt){
			temp = 0; dispInt = 0;			
		}			
		
		CrazyCalculator.makeThreadSleep();			
		return array.remove();
	}
	
	public String peek(){
		return array.get(getSize()-1).getValue(); 
	}
	
	public int getSize(){
		return array.getSize();
	}
	
	private void displayQueue(String input){
		/*
		if(input.equals("+"))
			CrazyCalculator.sShots.queueBlocks[dispInt++].setText("+");
		if(input.equals("-"))
			CrazyCalculator.sShots.queueBlocks[dispInt++].setText("-");
		if(input.equals("*"))
			CrazyCalculator.sShots.queueBlocks[dispInt++].setText("x");
		if(input.equals("/"))
			CrazyCalculator.sShots.queueBlocks[dispInt++].setText("/");
		if(input.equals("("))
			CrazyCalculator.sShots.queueBlocks[dispInt++].setText("(");
		if(input.equals(")"))
			CrazyCalculator.sShots.queueBlocks[dispInt++].setText(")");*/		
	}
	
}
