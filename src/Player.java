import java.util.ArrayList;

public class Player {
	private String player;
	private ArrayList<FieldContainer> battleField;
	private Ship ships;
	private int isHit;
	
	public Player(String p) {
		player = p;
		ships = new Ship();
		isHit = 0;
	}
	
	public void setField(ArrayList<FieldContainer> bf) {
		battleField = bf;
	}
	
	public int getHits() {
		return isHit;
	}
	
	
}
