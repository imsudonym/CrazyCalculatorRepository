
public class PseudoArray {
	
	public static int dispInt = 0;
	public static int temp = 0;
	private int size;	
	public static int ctr = 0;
	private LinkedList list = new LinkedList();	
	
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
			
		}else{
			throw new java.lang.RuntimeException("ArrayIndexOutOfBounds.");
		}
		
	}
	
	public Link get(int index){
		return list.get(index);
	}
	
	public String remove(){
		ctr--;		
		
		String data = list.remove(); 
		
		if(temp < 20)
			CrazyCalculator.sShots.arrayBlocks[temp++].setText("");
		
		if(temp == dispInt){
			temp = 0;  dispInt = 0;			
		}
		
		return data;
		
	}
	
	public int getSize(){
		return ctr;
	}	
	
	private void display(String input){
		
		if(input.equals("+"))
			CrazyCalculator.sShots.arrayBlocks[dispInt++].setText("+");
		if(input.equals("-"))
			CrazyCalculator.sShots.arrayBlocks[dispInt++].setText("-");
		if(input.equals("*"))
			CrazyCalculator.sShots.arrayBlocks[dispInt++].setText("x");
		if(input.equals("/"))
			CrazyCalculator.sShots.arrayBlocks[dispInt++].setText("/");
		if(input.equals("("))
			CrazyCalculator.sShots.arrayBlocks[dispInt++].setText("(");
		if(input.equals(")"))
			CrazyCalculator.sShots.arrayBlocks[dispInt++].setText(")");		
	}
}
