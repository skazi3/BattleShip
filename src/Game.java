import java.net.*;
import java.util.ArrayList;
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game extends JFrame
{  
	//2 user Grids and 2 opponent grids
	private ArrayList<Container> userField;
	private ArrayList<Container> opponentField;
	private Container container;
	private int userPlayer = 0;
	private int opponentPlayer = 1;
   // set up GUI
   public Game()
   {
      super( "BattleField" );
     
      setJMenuBar(MenuBar());
      JPanel statusBar = new JPanel();
      JLabel status = new JLabel();
     
      
      container = getContentPane();
      container.setLayout (new BorderLayout());
      
      statusBar.setLayout(new BorderLayout());
      statusBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
      statusBar.setBackground(Color.LIGHT_GRAY);
      status.setText("Status: ");
      statusBar.add(status, BorderLayout.WEST);
      add("South", statusBar);
      
      //west panel should have something else (???)
      container.add(makeGrids(), BorderLayout.WEST);
      container.setBackground(Color.LIGHT_GRAY);
      Player user = new Player(userPlayer);
      Player opponent = new Player(opponentPlayer);
      
     
      setSize( 650, 630);
      setVisible( true );

   } // end constructor
   
   //creates four 10x10 grids with row/col identifiers
   private Container makeGrids() {
	   Container battleField = new Container();
	   battleField.setLayout(new GridLayout(2, 2, 4, 4));
	   userField = new ArrayList<Container>();
	   opponentField = new ArrayList<Container>();
	   
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
	   JMenuItem battleShip = new JMenuItem("battleship: 4");
	   JMenuItem destroyer = new JMenuItem("Destroyer: 3");
	   JMenuItem submarine = new JMenuItem("Submarine: 3");
	   JMenuItem patrolBoat = new JMenuItem("Patrol Boat: 2");

	   //action listener stuff
	   exit.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   dispose();
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
   
   //begin main
   public static void main( String args[] )
   { 
      Game application = new Game();
      application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
   }



 } // end class Game




 
