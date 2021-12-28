package view;

import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import models.Automatehandler;
import models.MutantCircle;
import models.State;
import models.Transition;

public class JPanelAutomate extends JPanel {
	private static final long serialVersionUID = 1L;

	private GraphicTransition graphicTransition;
	private ArrayList<Arrow> arrows;
	private ArrayList<GraphicSymbol> graphicSymbols;
	private ArrayList<StateFigure> stateFigures;
	private Automatehandler automatehandler;
	private boolean validateTransitionPainted;
	private boolean isStateActivated;
	private boolean isTransitionActivated;
	private int transitionOriginX = 0;
	private int transitionOriginY = 0;
	private int transitionEndX = 0;
	private int transitionEndY = 0;
	private int count = 0;

	public JPanelAutomate(GraphicTransition graphicTransition, Automatehandler automatehandler) {
		validateTransitionPainted = true;
		this.graphicTransition = graphicTransition;
		this.graphicTransition.setjPanelAutomate(this);
		this.automatehandler = automatehandler;
		automatehandler.setJpanelAutoamte(this);
		initLists();
		this.setLayout(null);
		initListener();
	}

	public void initLists() {
		arrows = new ArrayList<Arrow>();
		graphicSymbols = new ArrayList<GraphicSymbol>();
		stateFigures = new ArrayList<StateFigure>();
	}

	public void appendLine(Arrow arrow, Transition transition) {
		arrows.add(arrow);
		add(new TransitionSymbolCatcher((int) ((arrow.getX1() + arrow.getX2()) / 2),
				(int) ((arrow.getY1() + arrow.getY2()) / 2), transition, graphicTransition));
	}

	public void requestNewSymbol(Arrow arrow, Transition logical_transition) {
		add(new TransitionSymbolCatcher((int) ((arrow.getX1() + arrow.getX2()) / 2),
				(int) ((arrow.getY1() + arrow.getY2()) / 2), logical_transition, graphicTransition));

	}

	public void appendSymbol(String value, int x, int y) {
		graphicSymbols.add(graphicTransition.appendSymbol(new GraphicSymbol(value, x, y)));
		automatehandler.addSymbol(value);
	}

	protected void appendStateFigure(StateFigure stateFigure) {
		stateFigures.add(stateFigure);
	}

	private void paintLines(Graphics2D g2) {
		if (validateTransitionPainted) {
			g2.setStroke(new BasicStroke((float) 1.5));
			for (Arrow arrow : arrows) {
				MutantCircle circle = new MutantCircle(arrow.getX2(), arrow.getY2(), 25);
				if ((arrow.getX1() - arrow.getX2()) * (arrow.getX1() - arrow.getX2())
						+ (arrow.getY1() - arrow.getY2()) * (arrow.getY1() - arrow.getY2()) > ((25) * (25))) {
					g2.drawLine((int) arrow.getX1(), (int) arrow.getY1(), (int) arrow.getX2(), (int) arrow.getY2());
					g2.setColor(Color.blue);
					g2.drawLine((int) circle.intersectAwn1(arrow),
							(int) arrow.getAwn1YFromX(circle.intersectAwn1(arrow)), (int) arrow.getX2(),
							(int) arrow.getY2());
					g2.setColor(Color.red);
					g2.drawLine((int) circle.intersectAwn2(arrow),
							(int) arrow.getAwn2YFromX(circle.intersectAwn2(arrow)), (int) arrow.getX2(),
							(int) arrow.getY2());
					g2.setColor(Color.black);
				}
			}
		}
	}

	private void paintSymbols(Graphics2D g2) {
		for (GraphicSymbol graphicSymbol : graphicSymbols) {
			g2.drawString(graphicSymbol.getValue(), graphicSymbol.getX(), graphicSymbol.getY());
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		paintLines(g2);
		paintSymbols(g2);
		if(isTransitionActivated) {
			g2.drawLine(transitionOriginX,transitionOriginY, transitionEndX, transitionEndY);
		}
		this.repaint();
	}

	public boolean isStateActivated() {
		return isStateActivated;
	}

	public void setStateActivated(boolean isStateActivated) {
		this.isStateActivated = isStateActivated;
	}

	private void initListener() {
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (isStateActivated) {
					StateFigure stateFigure = new StateFigure(count, myInstance(), graphicTransition, e.getX(),
							e.getY());
					automatehandler.addState(stateFigure.getState());
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							myInstance().add(stateFigure);
							stateFigure.setSize((int) stateFigure.getMySize(), (int) stateFigure.getMySize());
							stateFigure.setVisible(true);
							myInstance().repaint();
						}
					});
					count++;
				}
			}

		});

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if (isTransitionActivated) {
					transitionEndX = e.getX();
					transitionEndY =  e.getY();
				}
			}
		});
	}

	private JPanelAutomate myInstance() {
		return this;
	}

	public void notifyInitialState(State state) {
		automatehandler.setInitialState(state);
	}

	public void validateTransitionPainted(boolean b) {
		validateTransitionPainted = b;
	}

	public void initChasingLine(int x, int y) {
		setListenerRecursively(myInstance());
		transitionOriginX = x;
		transitionOriginY = y;
		isTransitionActivated = true;
	}
	
	public void stopChasingLine() {
		isTransitionActivated = false;
		transitionOriginX = 0;
		transitionOriginY = 0;
		transitionEndX = 0;
		transitionEndY = 0;
	}
	
	public void setListenerRecursively(JComponent component) {
		if (component instanceof StateFigure) {
			((StateFigure) component).setTransitionOnGoing(true);
		}
		if (component.getComponents().length > 0) {
			for (int i = 0; i < component.getComponents().length; i++) {
				if (component.getComponent(i) instanceof JComponent)
					setListenerRecursively((JComponent) component.getComponent(i));
			}
		}
	}
	
	public void setTransitionEndX(int transitionEndX) {
		this.transitionEndX = transitionEndX;
	}
	
	public void setTransitionEndY(int transitionEndY) {
		this.transitionEndY = transitionEndY;
	}
	
	public int getTransitionEndX() {
		return transitionEndX;
	}
	
	public int getTransitionEndY() {
		return transitionEndY;
	}

}
