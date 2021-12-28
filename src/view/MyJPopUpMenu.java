package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import javax.swing.JPopupMenu;
import java.awt.event.ActionEvent;

public class MyJPopUpMenu extends JPopupMenu{
	
	private JMenuItem jMenuItemInitial;
	private JMenuItem jMenuItemFinal;
	private StateFigure stateFigure; 
	
	public MyJPopUpMenu(StateFigure stateFigure) {
		this.jMenuItemFinal = new JMenuItem("final");
		this.jMenuItemInitial = new JMenuItem("Inicial");
		this.stateFigure = stateFigure;
		add(jMenuItemInitial);
		add(jMenuItemFinal);
		setLayout(new GridLayout(2,1));
		setInvoker(stateFigure);
		initFinalListener();
		initInitialListener();
	}
	private void initInitialListener() {
		jMenuItemInitial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {              
				stateFigure.setAsInitial();
				getMyInstance().setVisible(false);
			}  
		});
	}
	
	private void initFinalListener() {
		jMenuItemFinal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {              
                stateFigure.setAsFinal();
                getMyInstance().setVisible(false);
            }  
		});
	}
	
	private MyJPopUpMenu getMyInstance() {
		return this;
	}
	
}
