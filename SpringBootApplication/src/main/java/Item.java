/**
 * @author vmangipu
 * 
 */

public class Item implements Comparable {
	private long satisfaction;
	private int time;

	public long getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfacion(long satisfaction) {
		this.satisfaction = satisfaction;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public Item(Object description, Object time){
		this.satisfaction=Long.parseLong(description.toString());
		this.time=Integer.parseInt(time.toString());
		
	}
	
	public String toString(){
		return "Satisfaction :"+satisfaction+ "  : Time :"+time; 
	}

	public int compareTo(Object arg0) {
		if(((Item)arg0).getTime()>this.time){		return 1;}
		if(((Item)arg0).getTime()<this.time){		return -1;}
		return 0;
		
	}
	Item( int i){
	setTime(i);
	}

}
