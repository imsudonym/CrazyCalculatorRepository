public class LinkedList{
	
	public Link last = null;
	public Link first = null;
	public static int dispInt = 0;
	public static int ctr = 0;
	
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
		
		display(newLink.getValue());
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
		
		if(ctr < 20)
			CrazyCalculatorMain.sShots.linkListBlocks[ctr++].setText("");
		
		if(ctr == dispInt){
			ctr = 0; dispInt = 0;
		}
			
		
		while(!isEmpty()){
			temp = first;
			
			first = first.next;					
			
			return temp.getValue();
		}				
		
		return null;
	}
	
	private void display(String input){
		
		if(input.equals("+"))
			CrazyCalculatorMain.sShots.linkListBlocks[dispInt++].setText("+");
		if(input.equals("-"))
			CrazyCalculatorMain.sShots.linkListBlocks[dispInt++].setText("-");
		if(input.equals("*"))
			CrazyCalculatorMain.sShots.linkListBlocks[dispInt++].setText("x");
		if(input.equals("/"))
			CrazyCalculatorMain.sShots.linkListBlocks[dispInt++].setText("/");
		if(input.equals("("))
			CrazyCalculatorMain.sShots.linkListBlocks[dispInt++].setText("(");
		if(input.equals(")"))
			CrazyCalculatorMain.sShots.linkListBlocks[dispInt++].setText(")");		
	}
		
}