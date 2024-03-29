import java.net.*;
import java.util.ArrayList;
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class BattleShipClient extends JFrame {
 
	//2 user Grids and 2 opponent grids
		private FieldContainer battleField;
		private AttackContainer attackField;

		private Container container;
		private String player;
		private StatusBar statusBar;
		private  Player client;
		//server stuff
		boolean serverContinue;
		ServerSocket serverSocket;
		
		PrintWriter out;
		BufferedReader in;
		int rounds = 0;
	   // set up GUI
	   public BattleShipClient(String p)
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
	   

	  
	   
	   //add to menu stuff
	   file.add(about);
	   file.add(exit);
       about.addActionListener(new ActionListener() {  // anonymous inner class
                   // display message dialog when user selects About...
                   public void actionPerformed(ActionEvent event) {
                       JOptionPane.showMessageDialog(BattleShipClient.this,
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
                       JOptionPane.showMessageDialog(BattleShipClient.this,
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
	   
       connection.addActionListener(new ActionListener() {
    	   		public void actionPerformed(ActionEvent e) {
    	   			ConnectionClientListener c = new ConnectionClientListener(battleField);
    	   			attackField.addConnection(c);
    	   			
    	   		}
       });
      
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
 class ConnectionClientListener extends JFrame implements ActionListener{
		  JButton sendButton;
		  JButton connectButton;
		  JTextField machineInfo;
		  JTextField portInfo;
		  JTextField message;
		  JTextArea history;
		  FieldContainer battleField;

		  // Network Items
		  boolean connected;
		  Socket echoSocket;
		  PrintWriter out;
		  BufferedReader in;

		   // set up GUI
		   public ConnectionClientListener(FieldContainer fc)
		   {
			   super("Client connection");
			   battleField = fc;
		      // get content pane and set its layout
		      Container container = getContentPane();
		      container.setLayout (new BorderLayout ());
		      
		      // set up the North panel
		      JPanel upperPanel = new JPanel ();
		      upperPanel.setLayout (new GridLayout (4,2));
		      container.add (upperPanel, BorderLayout.NORTH);
		      
		      // create buttons
		      connected = false;

		      upperPanel.add ( new JLabel ("Message: ", JLabel.RIGHT) );
		      message = new JTextField ("");
		      message.addActionListener( this );
		      upperPanel.add( message );
		      
		      sendButton = new JButton( "Send Message" );
		      sendButton.addActionListener( this );
		      sendButton.setEnabled (false);
		      upperPanel.add( sendButton );

		      connectButton = new JButton( "Connect to Server" );
		      connectButton.addActionListener( this );
		      upperPanel.add( connectButton );
		                      
		      upperPanel.add ( new JLabel ("Server Address: ", JLabel.RIGHT) );
		      machineInfo = new JTextField ("127.0.0.1");
		      upperPanel.add( machineInfo );
		                      
		      upperPanel.add ( new JLabel ("Server Port: ", JLabel.RIGHT) );
		      portInfo = new JTextField ("");
		      upperPanel.add( portInfo );
		                      
		      history = new JTextArea ( 10, 40 );
		      history.setEditable(false);
		      container.add( new JScrollPane(history) ,  BorderLayout.CENTER);

		      setSize( 500, 250 );
		      setVisible( true );

		   } // end CountDown constructor

		  
		    // handle button event
		    public void actionPerformed( ActionEvent event )
		    {
		       if ( connected && 
		           (event.getSource() == sendButton || 
		            event.getSource() == message ) )
		       {
		         doSendMessage();
		       }
		       else if (event.getSource() == connectButton)
		       {
		         doManageConnection();
		       }
		    }

		    public void doSendMessage()
		    {
		      try
		      {
		        out.println(message.getText());
		        history.insert ("From Server: " + in.readLine() + "\n" , 0);
		      }
		      catch (IOException e) 
		      {
		        history.insert ("Error in processing message ", 0);
		      }
		    }
		    
		    public void doSendCoords(char c, int y)
		    {
		    		String newC = Character.toString(c);
		    		String newY = Integer.toString(y);
		    		System.out.println("newC+newy: "+ newC + "," + newY);
		    		String attack = newC + newY;
		    		out.println(attack);

		    } 
		    public void doManageConnection()
		    {
		      if (connected == false)
		      {
		        String machineName = null;
		        int portNum = -1;
		        try {
		            machineName = machineInfo.getText();
		            portNum = Integer.parseInt(portInfo.getText());
		            echoSocket = new Socket(machineName, portNum );
		            out = new PrintWriter(echoSocket.getOutputStream(), true);
		            in = new BufferedReader(new InputStreamReader(
		                                        echoSocket.getInputStream()));
		            sendButton.setEnabled(true);
		            connected = true;
		            connectButton.setText("Disconnect from Server");
		           // new CommunicationClientThread(echoSocket,this, battleField);
		        } catch (NumberFormatException e) {
		            history.insert ( "Server Port must be an integer\n", 0);
		        } catch (UnknownHostException e) {
		            history.insert("Don't know about host: " + machineName , 0);
		        } catch (IOException e) {
		            history.insert ("Couldn't get I/O for "
		                               + "the connection to: " + machineName , 0);
		        }

		      }
		      else
		      {
		        try 
		        {
		          out.close();
		          in.close();
		          echoSocket.close();
		          sendButton.setEnabled(false);
		          connected = false;
		          connectButton.setText("Connect to Server");
		        }
		        catch (IOException e) 
		        {
		            history.insert ("Error in closing down Socket ", 0);
		        }
		      }   
		    }
	  }
 /*
 class CommunicationClientThread extends Thread
	{ 
	 //private boolean serverContinue = true;
	 private Socket serverSocket;
	 private ConnectionClientListener gui;
	 private FieldContainer battleField;

	 public CommunicationClientThread (Socket clientSoc, ConnectionClientListener ec3, FieldContainer bf)
	   {
		 serverSocket = clientSoc;
	    gui = ec3;
	    battleField = bf;
	    gui.history.insert ("Communicating with Port" + serverSocket.getLocalPort()+"\n", 0);
	    start();
	   }

	 public void run()
	   {
	    System.out.println ("New Communication Thread Started");

	    try { 
	         PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), 
	                                      true); 
	         BufferedReader in = new BufferedReader( 
	                 new InputStreamReader( serverSocket.getInputStream())); 

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
	             } 

	         out.close(); 
	         in.close(); 
	         serverSocket.close(); 
	        } 
	    catch (IOException e) 
	        { 
	         System.err.println("Problem with Communication Server");
	         //System.exit(1); 
	        } 
	    }
	} 

*/
