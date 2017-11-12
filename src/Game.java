
public class Game {

	public static void main(String[] args) {
		BattleShip bsClient = new BattleShip("Client");
		BattleShip bsServer = new BattleShip("Server");
		
		bsClient.setDefaultCloseOperation(bsClient.EXIT_ON_CLOSE);
		bsServer.setDefaultCloseOperation(bsServer.EXIT_ON_CLOSE);
		

	}

}
