import java.net.*;
import java.util.ArrayList;
import java.io.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game extends JFrame
{  
	//four grids
	private ArrayList<Container> userField;
	private ArrayList<Container> opponentField;
	private Container container;

   // set up GUI
   public Game()
   {
      super( "BattleField" );
     
      // get content pane and set its layout
      container = getContentPane();
      container.setLayout (new BorderLayout());
      
      
      container.add(makeGrids(), BorderLayout.WEST);
      
      
     
      setSize( 650, 650 );
      setVisible( true );

   } // end constructor
   public Container makeGrids() {
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
   public static void main( String args[] )
   { 
      Game application = new Game();
      application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
   }



 } // end class Game




 
