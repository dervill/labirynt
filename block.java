package allegro;

public class block {
	
	private int x;						// koordynata 1
	private int y;						// koordynata 2
	
	boolean left = true;		// Zmienne sluzace do blokowania gdzie algorytm na 'skreczac'
	boolean up = true;
	boolean down = true;
	boolean right = true;
	
	public block(int a, int b){
		x=a;
		y=b;
	}
	
	public void set(int a, int b){
		this.x = a;
		this.y = b;
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	public void setLeft(boolean i){
		left = i;
	}
	public void setRight(boolean i){
		right = i;
	}
	public void setUp(boolean i){
		up = i;
	}
	public void setDown(boolean i){
		down = i;
	}
	
	public String toString(){
		return "["+x+","+y+"]";
	}

}
