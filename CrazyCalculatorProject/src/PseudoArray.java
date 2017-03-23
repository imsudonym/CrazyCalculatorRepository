
public class PseudoArray {
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
		}else{
			throw new java.lang.RuntimeException("Bad.");
		}
		
	}
	
	public Link get(int index){
		return list.get(index);
	}
	
	public String remove(){
		ctr--;
		return list.remove();
		
	}
	
	public int getSize(){
		return ctr;
	}
	
	public String display(){
		String s = list.display();
		s+="\n\nPseudoArray\n";
		
		int i = 0;
		System.out.println("ctr = "+ctr);
		
		while(i < ctr){
			String str = get(i).getValue();
			s+= str+" -->";
			i++;
		}
		return s;
	}
}
