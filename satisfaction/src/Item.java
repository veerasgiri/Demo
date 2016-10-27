/**
 * @author vmangipu
 * 
 */

public class Item implements Comparable {
	private int satisfaction;
	private int time;

	public int getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfacion(int satisfaction) {
		this.satisfaction = satisfaction;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public Item(Object description, Object time){
		this.satisfaction=Integer.parseInt(description.toString());
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
