import java.util.ArrayList;

public class Player {
	private int player;
	private ArrayList<FieldContainer> battleField;
	private Ship ships;
	
	public Player(int p) {
		player = p;
		ships = new Ship();
	}
	
	public void setField(ArrayList<FieldContainer> bf) {
		battleField = bf;
	}
}
