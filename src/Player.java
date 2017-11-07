import java.util.ArrayList;

public class Player {
	private int player;
	private ArrayList<FieldContainer> battleField;
	
	public Player(int p) {
		player = p;
	}
	
	public void setField(ArrayList<FieldContainer> bf) {
		battleField = bf;
	}
}
