import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		BattleShipClient bsClient = new BattleShipClient("Client");
		BattleShipServer bsServer = new BattleShipServer("Server");
		//To prevent display overlap when launched
		bsClient.setLocationRelativeTo(null);
		
		bsClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bsServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}

}
