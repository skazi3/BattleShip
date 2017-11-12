import java.net.*;
import java.util.ArrayList;
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class BattleShipClient extends JFrame {
 
	//2 user Grids and 2 opponent grids
	private ArrayList<FieldContainer> clientField;

	private Container container;
	private int clientPlayer = 0;

	private StatusBar statusBar;
	boolean connected;
	Socket echoSocket;
	PrintWriter out;
	BufferedReader in;
	int rounds = 0;
   // set up GUI
   public BattleShipClient()
   {
      super( "BattleShip Client" );
     
      setJMenuBar(MenuBar());

      container = getContentPane();
      container.setLayout (new BorderLayout());
      statusBar = new StatusBar();
      
      add("South", statusBar.getStatusBar());
      
      //west panel should have something else (???)
      container.add(makeGrids(), BorderLayout.WEST);
      container.setBackground(Color.LIGHT_GRAY);
      Player client = new Player(clientPlayer);

      client.setField(clientField);

     
      setSize( 325, 630);
      setVisible( true );

   } // end constructor

   public static void main( String args[] )
	{ 
	      BattleShipClient bsClient = new BattleShipClient();
	      bsClient.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
   //creates four 10x10 grids with row/col identifiers
   private Container makeGrids() {
	   Container battleField = new Container();
	   battleField.setLayout(new GridLayout(2, 1, 4, 4));
	   clientField = new ArrayList<FieldContainer>();

	   
	   for(int i = 0; i < 2; i++) {
		   FieldContainer c = new FieldContainer();
		   clientField.add(c.getContainer());
		   battleField.add(c);
	   }
	

	   
	   return battleField;
	   
   }
   //adds menu bar functions
   private JMenuBar MenuBar() {
	   JMenuBar menuBar = new JMenuBar();
	   //JMenu stuff
	   JMenu file = new JMenu("File");
	   JMenu help = new JMenu("Help");
	   JMenu connect = new JMenu("Connect");
	   JMenu ships =  new JMenu("Ships");
	   JMenu stats = new JMenu("Statistics");
	   
	   //menu item stuff
	   JMenuItem about = new JMenuItem("About");
	   JMenuItem exit = new JMenuItem("Exit");

	   JMenuItem statistics = new JMenuItem("View stats");
	  
	   //ships item stuff
	   JMenuItem aircraftCarrier = new JMenuItem("Aircraft Carrier: 5");
	   JMenuItem battleShip = new JMenuItem("Battleship: 4");
	   JMenuItem destroyer = new JMenuItem("Destroyer: 3");
	   JMenuItem submarine = new JMenuItem("Submarine: 3");
	   JMenuItem patrolBoat = new JMenuItem("Patrol Boat: 2");

	   //action listener stuff
	   exit.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   dispose();
		   }
	   });
	   
	   //action listener for ships
	   aircraftCarrier.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   clientField.get(1).setShipChosen('A', 5);
			   aircraftCarrier.setEnabled(false);
		   }
	   });
	   
	   //add to menu stuff
	   file.add(exit); 
	   file.add(about);
	   
	   //add to ships stuff
	   ships.add(aircraftCarrier);
	   ships.add(battleShip);
	   ships.add(destroyer);
	   ships.add(submarine);
	   ships.add(patrolBoat);
	   
	   //add to stats
	   stats.add(statistics);
	   
	   //add to menubar stuff
	   menuBar.add(file);
	   menuBar.add(help);
	   menuBar.add(ships);
	   menuBar.add(connect);
	   menuBar.add(stats);
	   
	   
	   return menuBar;
   }
	   
}
