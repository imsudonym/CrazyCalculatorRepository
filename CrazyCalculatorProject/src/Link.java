
public class Link {
	private String value;
	public Link next;
	public Link previous;
	public int key;
	
	public Link(String value, int i){
		this.value = value;
		this.next = null;
		key = i;
	}
	
	public String getValue(){
		return value;
	}
}
