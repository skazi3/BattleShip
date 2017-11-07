import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusBar {
	JPanel statusBar;
    JLabel status;
    public StatusBar() {
    		statusBar = new JPanel();
    		status = new JLabel();
    		
    		statusBar.setLayout(new BorderLayout());
        statusBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        statusBar.setBackground(Color.LIGHT_GRAY);
        status.setText("Status: ");
        statusBar.add(status, BorderLayout.WEST);
    }
    
    public JPanel getStatusBar() {
    		return statusBar;
    }
	
}
