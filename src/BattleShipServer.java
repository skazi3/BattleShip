import java.net.*;
import java.util.ArrayList;
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class BattleShipServer extends JFrame {
 
	//2 user Grids and 2 opponent grids
	private FieldContainer battleField;
	private AttackContainer attackField;

	private Container container;
	private String player;
	private StatusBar statusBar;
	private Player client;

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
      client = new Player(player);
      //west panel should have something else (???)
      container.add(makeGrids(), BorderLayout.WEST);
      container.setBackground(Color.LIGHT_GRAY);

      setSize( 325, 680);
      setVisible( true );

   } // end constructor

   //creates four 10x10 grids with row/col identifiers
   private Container makeGrids() {
	   Container c = new Container();
	   c.setLayout(new GridLayout(2, 1, 4, 4));

	   attackField = new AttackContainer(0);
	   battleField = new FieldContainer(1);
	   client.setFieldContainer(battleField);
	   client.setAttackContainer(attackField);
	   c.add(attackField);
	   c.add(battleField);
	   
	   return c;
	   
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
			   client.getFieldContainer().buttonCount = 0;
			   client.getFieldContainer().setShipChosen('A', 5);
			   aircraftCarrier.setEnabled(false);
		   }
	   });
	   battleShip.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   client.getFieldContainer().buttonCount = 0;
			   client.getFieldContainer().setShipChosen('B', 4);
			   battleShip.setEnabled(false);
		   }
	   });
	   destroyer.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   client.getFieldContainer().buttonCount = 0;
			   client.getFieldContainer().setShipChosen('D', 3);
			   destroyer.setEnabled(false);
		   }
	   });
	   submarine.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   client.getFieldContainer().buttonCount = 0;
			   client.getFieldContainer().setShipChosen('S', 3);
			   submarine.setEnabled(false);
		   }
	   });
	   patrolBoat.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   client.getFieldContainer().buttonCount = 0;
			   client.getFieldContainer().setShipChosen('P', 2);
			   patrolBoat.setEnabled(false);
		   }
	   });
	   
	   
	   //actionlistener for connect	
	   connection.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   ConnectionServerListener s = new ConnectionServerListener(battleField);
		   }
	   });
	   
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

class ConnectionServerListener extends JFrame implements ActionListener{
	// GUI items
	  JButton ssButton;
	  JLabel machineInfo;
	  JLabel portInfo;
	  JTextArea history;
	  JButton sendButton;
	  private boolean running;
	  private FieldContainer fc;

	  // Network Items
	  boolean serverContinue;
	  ServerSocket serverSocket;
	  JTextField message;
	  PrintWriter out;
	  BufferedReader in;

	   // set up GUI
	   public ConnectionServerListener(FieldContainer f)
	   {
	      super( "Connection Server" );

	      fc = f;
	      // get content pane and set its layout
	      Container container = getContentPane();
	      container.setLayout( new FlowLayout() );
	      
			// set up the North panel
			JPanel upperPanel = new JPanel ();
			upperPanel.setLayout (new GridLayout (4,2));
			container.add (upperPanel, BorderLayout.NORTH);

	      // create buttons
	      running = false;
	      ssButton = new JButton( "Start Listening" );
	      ssButton.addActionListener( this );
	      container.add( ssButton );

	      upperPanel.add ( new JLabel ("Message: ", JLabel.LEFT) );
	      message = new JTextField ("");
	      message.addActionListener( this );
	      upperPanel.add( message);
	      
	      sendButton = new JButton( "Send Message" );
	      sendButton.addActionListener( this );
	      sendButton.setEnabled (false);
	      upperPanel.add( sendButton );
	      
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
	      machineInfo = new JLabel (machineAddress);
	      //container.add( machineInfo );
	      portInfo = new JLabel (" Not Listening ");
	      container.add( portInfo );

	      history = new JTextArea ( 10, 40 );
	      history.setEditable(false);
	      container.add( new JScrollPane(history) );

	      setSize( 500, 250 );
	      setVisible( true );

	   } // end CountDown constructor


	    // handle button event
	    public void actionPerformed( ActionEvent event )
	    {

	       if (!running &&
		           (event.getSource() == sendButton || 
		            event.getSource() == message ) ) {
	    	   		doSendMessage();
	       }
	       else if (running == false)
	       {
	         new ConnectionThread (this, fc);
	         sendButton.setEnabled(true);
	       } 
	       else
	       {
	         serverContinue = false;
	         ssButton.setText ("Start Listening");
	         portInfo.setText (" Not Listening ");
	       }
	    }
	    
	    public void doSendMessage()
	    {
	      try
	      {
	            String machineName = machineInfo.getText();
	            int portNum = Integer.parseInt(portInfo.getText());
	            Socket echoSocket = new Socket(machineName, portNum );
	            out = new PrintWriter(echoSocket.getOutputStream(), true);
	            in = new BufferedReader(new InputStreamReader(
	                                        echoSocket.getInputStream()));
		        out.println(message.getText());
		        history.insert ("From Server: " + in.readLine() + "\n" , 0);
	      }
	      catch (IOException e) 
	      {
	        history.insert ("Error in processing message ", 0);
	      }
	    }


	 } // end class EchoServer3


	class ConnectionThread extends Thread
	 {
		ConnectionServerListener gui;
		private FieldContainer fc;
	   
	   public ConnectionThread (ConnectionServerListener es3, FieldContainer f)
	   {
	     gui = es3;
	     fc = f;
	     start();
	   }
	   
	   public void run()
	   {
	     gui.serverContinue = true;
	     
	     try 
	     { 
	       gui.serverSocket = new ServerSocket(0); 
	       gui.portInfo.setText("Listening on Port: " + gui.serverSocket.getLocalPort());
	       System.out.println ("Connection Socket Created");
	       try { 
	         while (gui.serverContinue)
	         {
	           System.out.println ("Waiting for Connection");
	           gui.ssButton.setText("Stop Listening");
	           new CommunicationThread (gui.serverSocket.accept(), gui, fc); 
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
	 private ConnectionServerListener gui;
	 private FieldContainer battleField;



	 public CommunicationThread (Socket clientSoc, ConnectionServerListener ec3, FieldContainer bf)
	   {
	    clientSocket = clientSoc;
	    gui = ec3;
	    battleField = bf;
	    gui.history.insert ("Communicating with Port" + clientSocket.getLocalPort()+"\n", 0);
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
	              char[] clientHit = inputLine.toCharArray();
	              battleField.sendAttack(clientHit);
	              
	        
	              
	              
	              
//------------------------FIRE AT SERVER BOARD HERE USING INPUTLINE----------------------------//
	              
	              gui.history.insert (inputLine+"\n", 0);
	              
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

