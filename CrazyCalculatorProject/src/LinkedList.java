public class LinkedList{
	
	public static String stringTemp = "";
	public Link last = null;
	public Link first = null;		
	
	public boolean isEmpty(){
		return (first == null);
	}
	
	/*
	public static void setStringTemp(String str){
		stringTemp = str;				
	}*/
	
	public void add(Link newLink){
		
		if(isEmpty()){
			
			last = newLink;			
			newLink.next = null;
			first = newLink;
		}
		else{
			
			last.next = newLink;
			last = last.next;
		}			
		
		display(newLink.getValue());
		CrazyCalculator.makeThreadSleep();
	}
	
	public Link get(int index){		
		Link current = first;
		
		while(current.key != index){			
			if(current.next == null){
				current = null;
				break;
			}else if(current.key == last.key){
				current = null;
				break;
			}else{
				current = current.next;
			}
		}
		
		return current;
	}
	
	public String remove(){
		Link temp = null;						
		String str = null;
		
		if(!isEmpty()){
			temp = first;
			
			first = first.next;					
			
			str = temp.getValue();
			
		}						
		
		if(str != null){
			if(!CrazyCalculator.evaluatingPostfix){
				if(stringTemp.length() > 0){
					stringTemp = stringTemp.substring(1, stringTemp.length());
				}
				
				CrazyCalculator.sShots1.linkListBlocks.setText(stringTemp);
			}else{
				
				if(stringTemp.length() > 0){
					if(stringTemp.indexOf(str) > 0)
						stringTemp = stringTemp.substring(0, stringTemp.indexOf(str));
					else
						stringTemp = "";
				}
				CrazyCalculator.sShots2.linkListBlocks.setText(stringTemp);	
			}
				
			CrazyCalculator.makeThreadSleep();
		
			return str;
		}
		
		return null;
	}
	
	private void display(String input){
		
		if(CrazyCalculator.evaluatingPostfix){			
			stringTemp += input;
			CrazyCalculator.sShots2.linkListBlocks.setText(stringTemp);
		}else{			
			stringTemp += input;
			CrazyCalculator.sShots1.linkListBlocks.setText(stringTemp);
		}					
	}
		
}