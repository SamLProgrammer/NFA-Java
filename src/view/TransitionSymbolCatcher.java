package view;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import models.Transition;

public class TransitionSymbolCatcher extends JTextField{

	private Transition transition;
	private int x, y;
	private GraphicTransition graphicTransition;
	
	public TransitionSymbolCatcher(int x, int y, Transition transition, GraphicTransition graphicTransition) {
		this.x = x;
		this.y = y;
		this.transition = transition;
		this.graphicTransition = graphicTransition;
		initComponents();
		initKeyListener();
		stealFocus();
	}

	public void stealFocus() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				myInstance().requestFocusInWindow();
			}
		});
	}
	
	private void initComponents() {
		this.setFont(new Font("Arial", Font.BOLD, 20));
		this.setBounds(x, y, 80, 40);
	}

	public void initKeyListener() {
	 addKeyListener(new KeyAdapter() {
		 @Override
		public void keyPressed(KeyEvent e) {
			 if(e.getKeyCode() == 10) {
				 myInstance().setVisible(false);
				 transition.setValue(myInstance().getText());
				 graphicTransition.reset();
				 graphicTransition.notifyPanelNewSymbol(transition.getValue(), x, y);
			 }
		}
	});
	}
	
	private TransitionSymbolCatcher myInstance() {
		return this;
	}
	
}
