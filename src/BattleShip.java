import java.net.*;
import java.util.ArrayList;
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class BattleShip extends JFrame {
 
	//2 user Grids and 2 opponent grids
	private ArrayList<FieldContainer> userField;
	private ArrayList<FieldContainer> opponentField;
	private Container container;
	private int userPlayer = 0;
	private int opponentPlayer = 1;
	private StatusBar statusBar;
	int rounds = 0;
   // set up GUI
   public BattleShip()
   {
      super( "BattleShip" );
     
      setJMenuBar(MenuBar());

      container = getContentPane();
      container.setLayout (new BorderLayout());
      statusBar = new StatusBar();
      
      add("South", statusBar.getStatusBar());
      
      //west panel should have something else (???)
      container.add(makeGrids(), BorderLayout.WEST);
      container.setBackground(Color.LIGHT_GRAY);
      Player user = new Player(userPlayer);
      Player opponent = new Player(opponentPlayer);
      user.setField(userField);
      opponent.setField(opponentField);
     
      setSize( 650, 630);
      setVisible( true );

   } // end constructor

   
   //creates four 10x10 grids with row/col identifiers
   private Container makeGrids() {
	   Container battleField = new Container();
	   battleField.setLayout(new GridLayout(2, 2, 4, 4));
	   userField = new ArrayList<FieldContainer>();
	   opponentField = new ArrayList<FieldContainer>();
	   
	   for(int i = 0; i < 4; i++) {
		   FieldContainer c = new FieldContainer();
		   if(i % 2 == 0)
			   userField.add(c.getContainer());
		   else
			   opponentField.add(c.getContainer());
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
			   userField.get(1).setShipChosen('A', 5);
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
