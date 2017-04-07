
public class PseudoArray {
	
	public static String stringTemp = "";
	private int size;	
	public static int ctr = 0;
	LinkedList list = new LinkedList();	
	
	public PseudoArray(int size){
		this.size = size;
	}
	
	public boolean isEmpty(){
		return list.isEmpty();
	}
	
	public void add(String value){
		if(ctr <= size){
			
			Link newLink = new Link(value, ctr);
			list.add(newLink);
			ctr++;
			
			display(value);
			CrazyCalculator.makeThreadSleep();
			
		}else{
			throw new java.lang.RuntimeException("ArrayIndexOutOfBounds.");
		}
		
	}
	
	public Link get(int index){
		return list.get(index);
	}
	
	public String remove(){
		
		String str = list.remove(); 	
		
		if(!CrazyCalculator.evaluatingPostfix){
			if(stringTemp.length() > 0){
				stringTemp = stringTemp.substring(1, stringTemp.length());
			}
			
			CrazyCalculator.sShots1.arrayBlocks.setText(stringTemp);
		}else{
			
			if(stringTemp.length() > 0){
				if(stringTemp.indexOf(str) > 0)
					stringTemp = stringTemp.substring(0, stringTemp.indexOf(str));
				else
					stringTemp = "";
			}
			CrazyCalculator.sShots2.arrayBlocks.setText(stringTemp);
		}	
		
		CrazyCalculator.makeThreadSleep();		
								
		ctr--;
		return str;
		
	}
	
	public int getSize(){
		return ctr;
	}	
	
	private void display(String input){
		
		if(CrazyCalculator.evaluatingPostfix){			
			stringTemp += input;
			CrazyCalculator.sShots2.arrayBlocks.setText(stringTemp);
		}else{
			stringTemp += input;
			CrazyCalculator.sShots1.arrayBlocks.setText(stringTemp);

		}			
	}
}
