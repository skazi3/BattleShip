import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;


public class FieldContainer extends Container {
	private JButton[][] coordinates;
	private JLabel row = new JLabel("         A      B      C     D     E      F    G     H     I      J ");
	private ArrayList<String> col;
	
	public FieldContainer() {

		coordinates = new JButton[10][10];
		initializeCol();
		setLayout(new BorderLayout());
		add(row, BorderLayout.NORTH);
		add(makeField(), BorderLayout.SOUTH);

		setSize(250, 250);
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
				coordinates[i][j] = new JButton(" ");
				coordinates[i][j].setPreferredSize(new Dimension(25, 25));
				/*if(j % 2 == 0 && i % 2 == 0)
					coordinates[i][j].setIcon(new ImageIcon("images/batt100.gif"));
				else
					coordinates[i][j].setIcon(new ImageIcon("images/batt101.gif"));*/
				fc.add(coordinates[i][j]);
			}
		}
		return fc;
	}
	public FieldContainer getContainer() {
		return this;
	}
}
