import java.net.*;
import java.util.ArrayList;
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class BattleShipServer extends JFrame {
 
	//2 user Grids and 2 opponent grids
	private ArrayList<FieldContainer> serverField;

	private Container container;
	private int serverPlayer = 0;

	private StatusBar statusBar;
	private boolean running;
	
	  // Network Items
	boolean serverContinue;
	ServerSocket serverSocket;
	PrintWriter out;
	BufferedReader in;
	int rounds = 0;
   // set up GUI
   public BattleShipServer()
   {
      super( "BattleShip Server" );
     
      setJMenuBar(MenuBar());
      running = false;
      container = getContentPane();
      container.setLayout (new BorderLayout());
      statusBar = new StatusBar();
      
      add("South", statusBar.getStatusBar());
      
      //west panel should have something else (???)
      container.add(makeGrids(), BorderLayout.WEST);
      container.setBackground(Color.LIGHT_GRAY);
      Player server = new Player(serverPlayer);

      server.setField(serverField);
      String machineAddress = null;
      try
      {  
        InetAddress addr = InetAddress.getLocalHost();
        machineAddress = addr.getHostAddress();
      }
      catch (UnknownHostException e)
      {
        machineAddress = "127.0.0.1";
      }
     
      setSize( 325, 630);
      setVisible( true );

   } // end constructor

   public static void main( String args[] )
	{ 
	   BattleShipServer bsServer = new BattleShipServer();
	   bsServer.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
   //creates four 10x10 grids with row/col identifiers
   private Container makeGrids() {
	   Container battleField = new Container();
	   battleField.setLayout(new GridLayout(2, 1, 4, 4));
	   serverField = new ArrayList<FieldContainer>();

	   
	   for(int i = 0; i < 2; i++) {
		   FieldContainer c = new FieldContainer();
		   serverField.add(c.getContainer());
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
	  
	   JMenuItem startConnection = new JMenuItem("Start Connection");
	   
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
			   serverField.get(1).setShipChosen('A', 5);
			   aircraftCarrier.setEnabled(false);
		   }
	   });
	   
	   //add to menu stuff
	   file.add(exit); 
	   file.add(about);
	   
	   connect.add(startConnection);
	   
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





