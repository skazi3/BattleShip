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
     

      container = getContentPane();
      container.setLayout (new BorderLayout());
      
      //west panel should have something else (???)
      container.add(makeGrids(), BorderLayout.EAST);
      container.setBackground(Color.white);
      setJMenuBar(MenuBar());
      setSize( 650, 600);
      setVisible( true );

   } // end constructor
   
   //creates four 10x10 grids with row/col identifiers
   private Container makeGrids() {
	   Container battleField = new Container();
	   battleField.setLayout(new GridLayout(2, 2, 4, 4));
	   userField = new ArrayList<Container>();
	   opponentField = new ArrayList<Container>();
	   
	   for(int i = 0; i < 2; i++) {
		   FieldContainer c = new FieldContainer();
		   userField.add(c.getContainer());
		   battleField.add(c);
	   }
	   for(int i = 0; i < 2; i++) {
		   FieldContainer c = new FieldContainer();
		   opponentField.add(c.getContainer());
		   battleField.add(c);
	   }

	   
	   return battleField;
	   
   }
   //adds menu bar functions
   private JMenuBar MenuBar() {
	   JMenuBar mb = new JMenuBar();
	   
	   JMenu exit = new JMenu("Exit");
	   JMenu about = new JMenu("About");
	   JMenu help = new JMenu("Help");
	   JMenu connect = new JMenu("Connect");
	   
	   exit.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   dispose();
		   }
	   });
	   mb.add(exit);
	   mb.add(about);
	   mb.add(help);
	   mb.add(connect);
	   
	   return mb;
   }
   //begin main
   public static void main( String args[] )
   { 
      Game application = new Game();
      application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
   }



 } // end class Game




 
