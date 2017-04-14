public class Queue{
	
	public static String stringTemp = "";	
	PseudoArray array = new PseudoArray(100);	
	
	public boolean isEmpty(){
		return array.isEmpty();
	}
	
	public void enqueue(String input){
					
		array.add(input);
		
		displayQueue(input);		
		CrazyCalculator.makeThreadSleep();
	}
	
	public String dequeue(){
					
		String str = array.remove();
						
		if(!CrazyCalculator.evaluatingPostfix){
			
			if(stringTemp.length() > 0)
				stringTemp = stringTemp.substring(1, stringTemp.length());			
			
			CrazyCalculator.sShots1.queueBlocks.setText(stringTemp);
		}else{
			
			if(stringTemp.length() > 0)					
				stringTemp = stringTemp.substring(str.length(), stringTemp.length());
			
			CrazyCalculator.sShots2.queueBlocks.setText(stringTemp);						
		}	
				
		CrazyCalculator.makeThreadSleep();			
		
		return str;
	}
	
	public String peek(){
		return array.get(getSize()-1).getValue(); 
	}
	
	public int getSize(){
		return array.getSize();
	}
	
	private void displayQueue(String input){
		
		if(CrazyCalculator.evaluatingPostfix){			
			stringTemp += input;
			CrazyCalculator.sShots2.queueBlocks.setText(stringTemp);
		}else{

			stringTemp += input;
			CrazyCalculator.sShots1.queueBlocks.setText(stringTemp);

		}	
	}
	
}
