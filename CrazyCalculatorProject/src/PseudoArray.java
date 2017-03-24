
public class PseudoArray {
	
	public static int dispInt = 0;
	public static int i = 0;
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
			
			//CrazyCalculatorMain.sShots.arrayBlocks[dispInt++].setText(value);
			
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
		//CrazyCalculatorMain.sShots.arrayBlocks[i++].setText("");
		
		return data;
		
	}
	
	public int getSize(){
		return ctr;
	}	
}
