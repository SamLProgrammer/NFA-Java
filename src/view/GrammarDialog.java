package view;

import javax.swing.JDialog;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Font;

public class GrammarDialog extends JDialog{
	
	private JTextArea txtArea;

	public GrammarDialog(String string) {
		initProperties();
		initComponents(string);
	}

	private void initComponents(String string) {
		txtArea = new JTextArea(string);
		txtArea.setFont(new Font("Arial", Font.BOLD, 20));
		add(txtArea, BorderLayout.CENTER);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void initProperties() {
		setLayout(new BorderLayout());
		int screen_width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int screen_height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setSize(screen_width / 4, screen_height / 2);
		setLocation(screen_width/2 - getWidth()/2, screen_height/5);
	}
}
