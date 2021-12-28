package view;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class StupidButton extends JButton{
	
	private JMainFrame mainFrame;
	private JTextField txtField;

	public StupidButton(String txt, JMainFrame mainFrame, JTextField txtField) {
		initComponents(txt, mainFrame, txtField);
		initListener();
	}

	private void initComponents(String txt, JMainFrame mainFrame, JTextField txtField) {
		this.mainFrame = mainFrame;
		this.txtField = txtField;
		setText(txt);
		setBackground(Color.red);
	}
	
	private void initListener() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mainFrame.callValidateWord(txtField.getText());
			}
		});
	}
	
	private StupidButton myInstance() {
		return this;
	}
}
