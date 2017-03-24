public class LinkedList{
	
	public Link last = null;
	public Link first = null;
	
	public boolean isEmpty(){
		return (first == null);
	}
	
	public void add(Link newLink){
		if(isEmpty()){
			
			last = newLink;			
			newLink.next = null;
			first = newLink;
		}
		else{
			
			last.next = newLink;
			//Link temp = last;
			last = last.next;
		}			
		
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
		
		while(!isEmpty()){
			temp = first;
			
			first = first.next;					
			
			return temp.getValue();
		}
		
		return null;
	}
		
}