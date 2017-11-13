import java.net.*;
import java.util.ArrayList;
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class BattleShipServer extends JFrame {
 
	//2 user Grids and 2 opponent grids
	private ArrayList<FieldContainer> clientField;

	private Container container;
	private String player;
	private StatusBar statusBar;

	//server stuff
	boolean serverContinue;
	ServerSocket serverSocket;
	
	PrintWriter out;
	BufferedReader in;
	int rounds = 0;
   // set up GUI
   public BattleShipServer(String p)
   {
      super( "BattleShip " + p );
      player = p;
      setJMenuBar(MenuBar());
      if(p == "Server") {
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
      }
      
      container = getContentPane();
      container.setLayout (new BorderLayout());
      statusBar = new StatusBar();
      
      add("South", statusBar.getStatusBar());
      
      //west panel should have something else (???)
      container.add(makeGrids(), BorderLayout.WEST);
      container.setBackground(Color.LIGHT_GRAY);
      Player client = new Player(player);

      client.setField(clientField);

     
      setSize( 325, 680);
      setVisible( true );

   } // end constructor

   //creates four 10x10 grids with row/col identifiers
   private Container makeGrids() {
	   Container battleField = new Container();
	   battleField.setLayout(new GridLayout(2, 1, 4, 4));
	   clientField = new ArrayList<FieldContainer>();

	   
	   for(int i = 0; i < 2; i++) {
		   FieldContainer c = new FieldContainer(i);
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
	   
	   JMenuItem howToPlay = new JMenuItem("How to Play");

	   JMenuItem statistics = new JMenuItem("View stats");
	   
	   JMenuItem connection = new JMenuItem("Start Connection");
	  
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
			   clientField.get(1).buttonCount = 0;
			   clientField.get(1).setShipChosen('A', 5);
			   aircraftCarrier.setEnabled(false);
		   }
	   });
	   battleShip.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   clientField.get(1).buttonCount = 0;
			   clientField.get(1).setShipChosen('B', 4);
			   battleShip.setEnabled(false);
		   }
	   });
	   destroyer.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   clientField.get(1).buttonCount = 0;
			   clientField.get(1).setShipChosen('D', 3);
			   destroyer.setEnabled(false);
		   }
	   });
	   submarine.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   clientField.get(1).buttonCount = 0;
			   clientField.get(1).setShipChosen('S', 3);
			   submarine.setEnabled(false);
		   }
	   });
	   patrolBoat.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   clientField.get(1).buttonCount = 0;
			   clientField.get(1).setShipChosen('P', 2);
			   patrolBoat.setEnabled(false);
		   }
	   });
	   
	   
	   //actionlistener for connect
	   connection.addActionListener(new ConnectionListener(this, player));
	   
	   //add to menu stuff
	   file.add(about);
	   file.add(exit);
       about.addActionListener(

               new ActionListener() {  // anonymous inner class

                   // display message dialog when user selects About...
                   public void actionPerformed(ActionEvent event) {
                       JOptionPane.showMessageDialog(BattleShipServer.this,
                               "Jason Guo, jguo28\nSarah Kazi, skazi3\nSarah Ather\nProject 4 - BattleShip\nCS 342",
                               "About", JOptionPane.PLAIN_MESSAGE);
                   }

               }  // end anonymous inner class

       ); // end call to addActionListener
	   
	   help.add(howToPlay);
	   
       howToPlay.addActionListener(

               new ActionListener() {  // anonymous inner class

                   // display message dialog when user selects About...
                   public void actionPerformed(ActionEvent event) {
                       JOptionPane.showMessageDialog(BattleShipServer.this,
                               "1. First connect the server and client to play against opponent.\n" +
                                       "2. Place all the ships on bottom side of the board\n" +
                                       "3. Take turns attacking your opponent using the top side of the board.\n"+
                                       "4. After all 5 ships were knocked down on either side, the game ends" + 
                                       "and a winner is declared.\n" +
                                       "5. Don't forget to have fun!!!",
                               "How to play", JOptionPane.PLAIN_MESSAGE);
                   }

               }  // end anonymous inner class
    		   ); // end call to addActionListener
	   
	   //add to ships stuff
	   ships.add(aircraftCarrier);
	   ships.add(battleShip);
	   ships.add(destroyer);
	   ships.add(submarine);
	   ships.add(patrolBoat);
	   
	   //add to stats
	   stats.add(statistics);
	   
	   //connection items
	   connect.add(connection);
	   
	   //add to menubar stuff
	   menuBar.add(file);
	   menuBar.add(help);
	   menuBar.add(ships);
	   menuBar.add(connect);
	   menuBar.add(stats);
	   
	   
	   return menuBar;
   }
	   
}
class ConnectionListener implements ActionListener{
	BattleShipServer battleShip;
	String player;
	public ConnectionListener(BattleShipServer bs, String p) {
		battleShip = bs;
		player = p;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(player == "Server")
			new ConnectionThread (battleShip);
		
	}
	
}
class ConnectionThread extends Thread{
	BattleShipServer gui;
	   
   public ConnectionThread (BattleShipServer es3)
   {
     gui = es3;
     start();
   }
   
   public void run()
   {
     gui.serverContinue = true;
     
     try 
     { 
       gui.serverSocket = new ServerSocket(0); 
       
       System.out.println ("Connection Socket Created");
       try { 
         while (gui.serverContinue)
         {
           System.out.println ("Waiting for Connection");
           
           new CommunicationThread (gui.serverSocket.accept(), gui); 
         }
       } 
       catch (IOException e) 
       { 
         System.err.println("Accept failed."); 
         System.exit(1); 
       } 
     } 
     catch (IOException e) 
     { 
       System.err.println("Could not listen on port: 10008."); 
       System.exit(1); 
     } 
     finally
     {
       try {
         gui.serverSocket.close(); 
       }
       catch (IOException e)
       { 
         System.err.println("Could not close port: 10008."); 
         System.exit(1); 
       } 
     }
   }
}

class CommunicationThread extends Thread
{ 
 //private boolean serverContinue = true;
 private Socket clientSocket;
 private BattleShipServer gui;



 public CommunicationThread (Socket clientSoc, BattleShipServer ec3)
   {
    clientSocket = clientSoc;
    gui = ec3;  
    start();
   }

 public void run()
   {
    System.out.println ("New Communication Thread Started");

    try { 
         PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), 
                                      true); 
         BufferedReader in = new BufferedReader( 
                 new InputStreamReader( clientSocket.getInputStream())); 

         String inputLine; 

         while ((inputLine = in.readLine()) != null) 
             { 
              System.out.println ("Server: " + inputLine); 
              out.println(inputLine); 

              if (inputLine.equals("Bye.")) 
                  break; 

              if (inputLine.equals("End Server.")) 
                  gui.serverContinue = false; 
             } 

         out.close(); 
         in.close(); 
         clientSocket.close(); 
        } 
    catch (IOException e) 
        { 
         System.err.println("Problem with Communication Server");
         //System.exit(1); 
        } 
    }
} 

