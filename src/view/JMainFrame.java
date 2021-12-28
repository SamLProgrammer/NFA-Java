package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Automatehandler;

public class JMainFrame extends JFrame{
	private JPanelAutomate jPanelAutomate;
	private GraphicTransition graphicTransition;
	private Automatehandler automatehandler;
	private JTextField wordCatcher;

	public JMainFrame(Automatehandler automatehandler) {
		this.automatehandler = automatehandler;
		initComponents();
		addComponents();
	}

	private void addComponents() {
		this.add(jPanelAutomate);
	}

	private void initComponents() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setSize(2*width/3, 9*height/10);
		this.setLocation(width/2 - getWidth()/2,height/50);
	
		
		this.graphicTransition = new GraphicTransition();
		
		this.jPanelAutomate = new JPanelAutomate(graphicTransition, automatehandler);
		
		JPanel jPanelButtonsPanel = new JPanel();
		jPanelButtonsPanel.setLayout(new GridLayout(1, 5));
		
		MyButton myButtonTransition = new MyButton(Color.GRAY, this, false);
		MyButton myButtonState = new MyButton(Color.MAGENTA, this, true);
		
		wordCatcher = new JTextField();
		
		StupidButton validateWordButton = new StupidButton("Validate", this, wordCatcher);
		JButton showGrammarButton = new JButton("Show Grammar");
		
		showGrammarButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {		
				new GrammarDialog(automatehandler.generateGrammarForm());
			}
		});
		
		jPanelButtonsPanel.add(myButtonTransition);
		jPanelButtonsPanel.add(myButtonState);
		jPanelButtonsPanel.add(wordCatcher);
		jPanelButtonsPanel.add(validateWordButton);
		jPanelButtonsPanel.add(showGrammarButton);
		
		add(jPanelButtonsPanel, BorderLayout.NORTH);
		
		this.setVisible(true);
	}
	
	public void activateState() {
		jPanelAutomate.setStateActivated(true);
	}
	
	public void activateTransition() {
		jPanelAutomate.setStateActivated(false);
	}

	public void callValidateWord(String word) {
		boolean flag = automatehandler.validateWord(word);
		if(flag) {
			wordCatcher.setBackground(Color.decode("#8ced47"));
		} else {
			wordCatcher.setBackground(Color.decode("#f55353"));
		}
	}
	
}
