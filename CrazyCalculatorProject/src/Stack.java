
public class Stack {

	public static String stringTemp;	
	Queue queue = new Queue();

	public Stack(){
		stringTemp = "";
	}
	
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
							
		if(!CrazyCalculator.evaluatingPostfix){
			if(stringTemp.length() > 0){
				stringTemp = stringTemp.substring(0, stringTemp.indexOf(str));
				CrazyCalculator.sShots1.stackBlocks.setText(stringTemp);	
			}					
		}else{
			if(stringTemp.length() > 0){
				stringTemp = stringTemp.substring(0, stringTemp.indexOf(str));						
				CrazyCalculator.sShots2.stackBlocks.setText(stringTemp);
			}			
		}

		return str;
				
	}
	
	public String peek(){
		return queue.peek();
	}	
}
