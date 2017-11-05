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

   // set up GUI
   public Game()
   {
      super( "BattleField" );
     
      setJMenuBar(MenuBar());
      container = getContentPane();
      container.setLayout (new BorderLayout());
      
      //west panel should have something else (???)
      container.add(makeGrids(), BorderLayout.WEST);
      container.setBackground(Color.white);
     
      setSize( 650, 600);
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
	   
	   //menu item stuff
	   JMenuItem about = new JMenuItem("About");
	   JMenuItem exit = new JMenuItem("Exit");
	   
	   //action listener stuff
	   exit.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   dispose();
		   }
	   });
	   
	   //add to menu stuff
	   file.add(exit); 
	   file.add(about);
	   
	   //add to menubar stuff
	   menuBar.add(file);
	   menuBar.add(help);
	   menuBar.add(connect);
	   
	   return menuBar;
   }
   //begin main
   public static void main( String args[] )
   { 
      Game application = new Game();
      application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
   }



 } // end class Game




 
