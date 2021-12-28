package view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class MyButton extends JButton{
	private JMainFrame jFrame;
	private boolean isState;
	

	public MyButton(Color color, JMainFrame jMFrame, boolean isState) {
		this.setBackground(color);
		this.jFrame = jMFrame;
		this.isState = isState;
		if(isState) {
			setText("State");
		} else {
			setText("Transition");
		}
		initListener();
	}
	
	
	private void initListener() {
		addMouseListener(new MouseAdapter() {
			@Override
			
			public void mousePressed(MouseEvent e) {
				if (isState) {
					jFrame.activateState();
				}else {
					jFrame.activateTransition();
				}
			}
		});
	}
}
