import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MyButton extends JButton{
	private char row;
	private int col;
	private boolean isOccupied;
	
	public MyButton(char r, int c, ImageIcon img) {
		super(img);
		isOccupied = false;
		row = r;
		col = c;
	}
	public MyButton(ImageIcon img) {
		super(img);
	}
	public char getRow() {
		return row;
	}
	public int getRowInt() {
		switch(row) {
		case 'A': return 0;
		case 'B': return 1;
		case 'C': return 2;
		case 'D': return 3;
		case 'E': return 4;
		case 'F': return 5;
		case 'G': return 6;
		case 'H': return 7;
		case 'I': return 8;
		case 'J': return 9;
		default : return -1;
		}
	}
	public int getCol() {
		return col;
	}
	public boolean getOccupied() {
		return isOccupied;
	}
	public void setOccupied(boolean o) {
		isOccupied = o;
	}
}
