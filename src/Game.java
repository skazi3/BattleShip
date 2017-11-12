import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		BattleShip bsClient = new BattleShip("Client");
		BattleShip bsServer = new BattleShip("Server");
		
		bsClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bsServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}

}
