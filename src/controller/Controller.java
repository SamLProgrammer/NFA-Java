package controller;

import models.Automatehandler;
import view.JMainFrame;

public class Controller {

	private JMainFrame jMainFrame;
	private Automatehandler automatehandler;
	
	public Controller() {
		this.automatehandler = new Automatehandler();
		jMainFrame = new JMainFrame(automatehandler);
	}
	
}
