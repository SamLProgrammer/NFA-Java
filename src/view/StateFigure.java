package view;

import javax.swing.JComponent;

import models.State;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Point;

public class StateFigure extends JComponent {

	private State state;
	private JPanelAutomate jPanelAutomate;
	private Color color;
	private int pressedX;
	private int pressedY;
	private double xCenter;
	private double yCenter;
	private final double size = 50;
	private String stateName;
	private GraphicTransition graphicTransition;
	private boolean transitionOnGoing;
	private ArrayList<ArrowHandler> arrowHandlersList;

	public StateFigure(int value, JPanelAutomate jPanelAutomate, GraphicTransition graphicTransition,  double x, double y) {
		stateName = "q" + value;
		xCenter = x;
		yCenter = y;
		this.graphicTransition = graphicTransition;
		this.jPanelAutomate = jPanelAutomate;
		color = Color.decode("#3262a8");
		setBounds((int)(xCenter - size/2) , (int)(yCenter - size/2), 20, 20);
		setVisible(true);
		setComponentPopupMenu(new MyJPopUpMenu(this));
		state = new State(stateName);
		arrowHandlersList = new ArrayList<ArrowHandler>();
		initListener();
	}

	private void initListener() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pressedX = e.getX();
				pressedY = e.getY();
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(!jPanelAutomate.isStateActivated()) {
						if(graphicTransition.getInitialState() == null) {
							graphicTransition.initTransition((int)xCenter, (int)yCenter, myInstance());
						} else {
							Arrow arrow = arrowExists(graphicTransition.getInitialStateFigure(), myInstance());
							if(arrow == null) {								
								arrowHandlersList.add(graphicTransition.endTransition((int)xCenter, (int)yCenter, myInstance(), arrow));
							} else {
								graphicTransition.endTransition((int)xCenter, (int)yCenter, myInstance(), arrow);
							}
							jPanelAutomate.stopChasingLine();
						}
					}
				}
			}
		});

		addMouseMotionListener(new MouseAdapter() {
			@Override 
			public void mouseMoved(MouseEvent e) {
				if(transitionOnGoing) {
					jPanelAutomate.setTransitionEndX((int)(xCenter));
					jPanelAutomate.setTransitionEndY((int)(yCenter));
//					jPanelAutomate.setTransitionEndX((int)(myInstance().getX() + e.getX()));
//					jPanelAutomate.setTransitionEndY((int)(myInstance().getY() + e.getY()));
				}
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				jPanelAutomate.stopChasingLine();
				graphicTransition.reset();
				Point pt = new Point(myInstance().getLocation());
				myInstance().setBounds(pt.x + e.getX() - pressedX, pt.y + e.getY() - pressedY, myInstance().getWidth(), myInstance().getHeight());
				xCenter = getX() + size/2;
				yCenter = getY() + size/2;
				graphicTransition.moveTransitions(myInstance());
			}
		});
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.fillOval(0, 0, (int)(size), (int)(size));
		g2.setColor(Color.white);
		int widthText = g2.getFontMetrics().stringWidth(stateName);
		g2.drawString(stateName, (int)(size/2 - widthText/2), (int)(size/2));
	}
	
	private Arrow arrowExists(StateFigure initialStateFigure, StateFigure myInstance) {
		Arrow arrow = null;
			for (ArrowHandler arrowHandler : arrowHandlersList) {
				if(arrowHandler.getInitialState() == graphicTransition.getInitialStateFigure() && arrowHandler.getDestinyState() == myInstance()) {
					arrow = arrowHandler.getArrow();
				}
			}
		return arrow;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void changeColor(Color color) {
		this.color = color;
	}

	private StateFigure myInstance() {
		return this;
	}

	public double getMySize() {
		return size;
	}
	
	public double getRadius() {
		return size/2;
	}

	public void setAsInitial() {
		state.setInitial(true);
		jPanelAutomate.notifyInitialState(state);
		changeColor(Color.green);
	}

	public void setAsFinal() {
		state.setFinal(true);
		changeColor(Color.red);
	}
	
	public void showAlias() {
		//fix this dude
	}
	
	public State getState() {
		return state;
	}

	public void addArrowHandler(ArrowHandler arrowHandler) {
		arrowHandlersList.add(arrowHandler);
	}

	public ArrayList<ArrowHandler> getArrowHandlersList() {
		return arrowHandlersList;
	}
	
	public double getXCenter() {
		return xCenter;
	}
	
	public double getYCenter() {
		return yCenter;
	}
	
	public boolean isTransitionOnGoing() {
		return transitionOnGoing;
	}
	
	public void setTransitionOnGoing(boolean transitionOnGoing) {
		this.transitionOnGoing = transitionOnGoing;
	}
}
