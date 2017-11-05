import java.awt.*;
import java.util.ArrayList;
import javax.swing.JButton;

public class FieldContainer extends Container {
	private JButton[][] coordinates;
	
	public FieldContainer() {
		coordinates = new JButton[10][10];
		setLayout(new GridLayout(10, 10, 1, 1));
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				coordinates[i][j] = new JButton(" ");
				coordinates[i][j].setPreferredSize(new Dimension(30, 30));
				add(coordinates[i][j]);
			}
		}
		setSize(250, 250);
		setVisible(true);
	}
	public FieldContainer getContainer() {
		return this;
	}
}
