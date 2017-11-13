import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		BattleShipServer bsClient = new BattleShipServer("Client");
		BattleShipServer bsServer = new BattleShipServer("Server");
		
		bsClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bsServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}

}
