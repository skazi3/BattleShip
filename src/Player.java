import java.util.ArrayList;

public class Player {
	private String player;
	private ArrayList<FieldContainer> battleField;
	private Ship ships;
	
	public Player(String p) {
		player = p;
		ships = new Ship();
	}
	
	public void setField(ArrayList<FieldContainer> bf) {
		battleField = bf;
	}
}
