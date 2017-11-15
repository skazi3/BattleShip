import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		BattleShipClient bsClient = new BattleShipClient("Client");
		//To prevent display overlap when launched
		bsClient.setLocationRelativeTo(null);
		BattleShipServer bsServer = new BattleShipServer("Server");
		
		bsClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bsServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}

}
