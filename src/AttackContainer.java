import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;


public class AttackContainer extends Container {
	private JButton[][] coordinates;
	private JLabel row = new JLabel("         A      B      C     D     E      F    G     H     I      J ");
	private ArrayList<String> col;
	private int shipSize = -1;
	public int buttonCount;
	boolean userAttacked = false;
	private Coordinates hit;
	private ConnectionClientListener c;
	


	public AttackContainer(int no) {
		hit = new Coordinates('k', 0);
		coordinates = new JButton[10][10];
		initializeCol();
		setLayout(new BorderLayout());
		add(row, BorderLayout.NORTH);
		add(makeField(), BorderLayout.CENTER);

		setSize(260, 280);
		setVisible(true);
	}
	private void initializeCol() {
		col = new ArrayList<String>();
		for(int i = 1; i <= 10; i++) {
			col.add(Integer.toString(i));
		}
	}
	

	private Container makeField() {
		Container fc = new Container();
		fc.setLayout(new GridLayout(10, 11, 1, 1));
		for(int i = 0; i < 10; i++) {
			fc.add(new JLabel("   "+ col.get(i)));
			for(int j = 0; j < 10; j++) {
/*---------------------makes checkerboard buttons----------------------------------------------------------*/
				if(i % 2 == j % 2)
					coordinates[i][j] = new MyButton(getRow(i), j, new ImageIcon(((new ImageIcon(
				            "images/batt100.gif").getImage()
				            .getScaledInstance(25, 25,
				                    java.awt.Image.SCALE_SMOOTH)))));
				else
					coordinates[i][j] = new MyButton(getRow(i), j, new ImageIcon(((new ImageIcon(
				            "images/batt101.gif").getImage()
				            .getScaledInstance(25, 25,
				                    java.awt.Image.SCALE_SMOOTH)))));
/*---------------------end checkerboard buttons----------------------------------------------------------*/
				
/*---------------------makes actionlistener--------------------------------------------------------------*/
				coordinates[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MyButton b = (MyButton)e.getSource();
						userAttacked = true;
						c.doSendCoords(b.getRow(), b.getCol());
			
						
					}
				});
/*---------------------end actionlistener----------------------------------------------------------------*/
				
				coordinates[i][j].setPreferredSize(new Dimension(25, 25));
				fc.add(coordinates[i][j]);
			}
		}
		return fc;
	}

	public AttackContainer getContainer() {
		return this;
	}
	
	private char getRow(int r) {
		switch(r) {
		case 0: return 'A';
		case 1: return 'B';
		case 2: return 'C';
		case 3: return 'D';
		case 4: return 'E';
		case 5: return 'F';
		case 6: return 'G';
		case 7: return 'H';
		case 8: return 'I';
		case 9: return 'J';

		default: return 'K';
		}
		
	}
	public void setShipChosen(char name, int size) {
		
		shipSize = size;
	}
	public void addConnection(ConnectionClientListener ccl) {
		c = ccl;
	}
	

}
