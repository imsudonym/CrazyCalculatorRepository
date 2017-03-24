import javax.swing.ImageIcon;

public class Queue {
	PseudoArray array = new PseudoArray(100);
	PseudoArray temp = new PseudoArray(100);
	
	
	private static int temp1 = 0;			
	private static int q = 0;
	private ImageIcon addIcon = new ImageIcon(getClass().getResource("/images/+.png"));
	private ImageIcon minusIcon = new ImageIcon(getClass().getResource("/images/-.png"));
	private ImageIcon mulIcon = new ImageIcon(getClass().getResource("/images/x.png"));
	private ImageIcon divIcon = new ImageIcon(getClass().getResource("/images/div.png"));
	private ImageIcon oppIcon = new ImageIcon(getClass().getResource("/images/(.png"));
	private ImageIcon clospIcon = new ImageIcon(getClass().getResource("/images/).png"));
	
	public boolean isEmpty(){
		return array.isEmpty();
	}
	
	public void enqueue(String input){
		/*
		if(input.equals("+"))
			CrazyCalculatorMain.sShots.queueBlocks[q++].setIcon(addIcon);
		if(input.equals("-"))
			CrazyCalculatorMain.sShots.queueBlocks[q++].setIcon(minusIcon);
		if(input.equals("*"))
			CrazyCalculatorMain.sShots.queueBlocks[q++].setIcon(mulIcon);
		if(input.equals("/"))
			CrazyCalculatorMain.sShots.queueBlocks[q++].setIcon(divIcon);
		if(input.equals("("))
			CrazyCalculatorMain.sShots.queueBlocks[q++].setIcon(oppIcon);
		if(input.equals(")"))
			CrazyCalculatorMain.sShots.queueBlocks[q++].setIcon(clospIcon);
		*/
		array.add(input);
	}
	
	public String dequeue(){
		
		/*
		//	show contents							
		q--;
		
		for(int i = 0; i < q; i++){
			CrazyCalculatorMain.sShots.queueBlocks[i].setIcon(CrazyCalculatorMain.sShots.queueBlocks[i].getIcon());
		}
		/////////////////////////*/
		
		return array.remove();
	}
	
	public String peek(){
		return array.get(getSize()-1).getValue(); 
	}
	
	public int getSize(){
		return array.getSize();
	}
	
	public String display(){
		String s = "";//array.display();
		s+="\nQueue\n";
		
		int i = 0;
		
		while(i != getSize()){
			s+= " "+array.get(i).getValue()+" -->";
			i++;
		}
		
		return s;
	}
}
