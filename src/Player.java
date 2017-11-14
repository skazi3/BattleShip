import java.util.ArrayList;

public class Player {
	private String player;
	private FieldContainer battleField;
	private AttackContainer attackField;
	private Ship ships;
	private int isHit;
	
	public Player(String p) {
		player = p;
		ships = new Ship();
		isHit = 0;
	}
	
	public void setFieldContainer(FieldContainer bf) {
		battleField = bf;
	}
	public void setAttackContainer(AttackContainer af) {
		attackField = af;
	}
	
	public int getHits() {
		return isHit;
	}
	
	
}
