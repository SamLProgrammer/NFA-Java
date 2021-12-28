package view;

import java.util.ArrayList;
import models.LinearFunction;
import models.MutantCircle;
import models.State;
import models.Transition;

public class GraphicTransition {

	private int x1, y1, x2, y2;
	private State initialState;
	private StateFigure initialStateFigure;
	private JPanelAutomate jPanelAutomate;
	private Arrow arrow;

	public GraphicTransition() {

	}

	public void initTransition(int x1, int y1, StateFigure stateFigure) {
		this.x1 = x1;
		this.y1 = y1;
		this.initialState = stateFigure.getState();
		this.initialStateFigure = stateFigure;
		arrow = new Arrow();
		jPanelAutomate.initChasingLine(x1, y1);
	}

	public ArrowHandler endTransition(int x2, int y2, StateFigure stateFigure, Arrow auxArrow) {
		this.x2 = x2;
		this.y2 = y2;
		Transition logical_transition = new Transition(initialState, stateFigure.getState());
		this.initialState.addTransition(logical_transition);
		if (auxArrow == null) {
			arrow.setX1(initialStateFigure.getXCenter());
			arrow.setY1(initialStateFigure.getYCenter());
			arrow.setX2(stateFigure.getXCenter());
			arrow.setY2(stateFigure.getYCenter());
			MutantCircle circle = new MutantCircle(stateFigure.getXCenter(), stateFigure.getYCenter(), 25);
			double newXCenter = circle.intersectLine(arrow);
			LinearFunction line = new LinearFunction(arrow);
			arrow.setX2(newXCenter);
			arrow.setY2(line.getYFromX(newXCenter));
			arrow.setAwn1(calculateAwnFunction((int) arrow.getX1(), (int) arrow.getY1(), (int) arrow.getX2(),
					(int) arrow.getY2(), 0));
			arrow.setAwn2(calculateAwnFunction((int) arrow.getX1(), (int) arrow.getY1(), (int) arrow.getX2(),
					(int) arrow.getY2(), 50));
			jPanelAutomate.appendLine(arrow, logical_transition);
			initialStateFigure.addArrowHandler(new ArrowHandler(arrow, false, initialStateFigure, stateFigure));
		} else {
			arrow = auxArrow;
			jPanelAutomate.requestNewSymbol(arrow, logical_transition);
		}
		return new ArrowHandler(arrow, true, initialStateFigure, stateFigure);
	}

	private LinearFunction calculateAwnFunction(int x1, int y1, int x2, int y2, int complement) {
		double m1 = (double) (y2 - y1) / (double) (x2 - x1);
		double angle1 = Math.toDegrees(Math.atan(m1));
		double goal_angle = 180 - 25 - angle1;
		double angle_reason = Math.tan(Math.toRadians(goal_angle + complement)) * -1;
		double function_x1 = x2 - (double) y2 / (double) angle_reason;
		double b = (function_x1 * angle_reason) * -1;
		return new LinearFunction(angle_reason, b);
	}

	public void moveTransitions(StateFigure stateFigure) {
		ArrayList<ArrowHandler> arrowHandlersList = stateFigure.getArrowHandlersList();
		for (ArrowHandler arrowHandler : arrowHandlersList) {
			Arrow auxArrow = arrowHandler.getArrow();
			if (arrowHandler.isDestiny()) {
				auxArrow.setX2(stateFigure.getXCenter());
				auxArrow.setY2(stateFigure.getYCenter());
				MutantCircle circle = new MutantCircle(stateFigure.getXCenter(), stateFigure.getYCenter(), 25);
				double newXCenter = circle.intersectLine(auxArrow);
				LinearFunction line = new LinearFunction(auxArrow);
				if (auxArrow.getX1() == auxArrow.getX2()) {
					if (auxArrow.getY1() < auxArrow.getY2()) {
						auxArrow.setY2(stateFigure.getYCenter() - 25);
					} else {
						auxArrow.setY2(stateFigure.getYCenter() + 25);
					}
				} else {
					auxArrow.setX2(newXCenter);
					auxArrow.setY2(line.getYFromX(newXCenter));
				}
				auxArrow.setAwn1(calculateAwnFunction((int) auxArrow.getX1(), (int) auxArrow.getY1(),
						(int) auxArrow.getX2(), (int) auxArrow.getY2(), 0));
				auxArrow.setAwn2(calculateAwnFunction((int) auxArrow.getX1(), (int) auxArrow.getY1(),
						(int) auxArrow.getX2(), (int) auxArrow.getY2(), 50));
			} else {
				auxArrow.setX1(stateFigure.getXCenter());
				auxArrow.setY1(stateFigure.getYCenter());
				auxArrow.setX2(arrowHandler.getDestinyState().getXCenter());
				auxArrow.setY2(arrowHandler.getDestinyState().getYCenter());
				MutantCircle circle = new MutantCircle(arrowHandler.getDestinyState().getXCenter(),
						arrowHandler.getDestinyState().getYCenter(), 25);
				double newXCenter = circle.intersectLine(auxArrow);
				LinearFunction line = new LinearFunction(auxArrow);
				if (auxArrow.getX1() == auxArrow.getX2()) {
					if (auxArrow.getY1() < auxArrow.getY2()) {
						auxArrow.setY2(arrowHandler.getDestinyState().getYCenter() - 25);
					} else {
						auxArrow.setY2(arrowHandler.getDestinyState().getYCenter() + 25);
					}
				} else {
					auxArrow.setX2(newXCenter);
					auxArrow.setY2(line.getYFromX(newXCenter));
				}
				auxArrow.setAwn1(calculateAwnFunction((int) auxArrow.getX1(), (int) auxArrow.getY1(),
						(int) auxArrow.getX2(), (int) auxArrow.getY2(), 0));
				auxArrow.setAwn2(calculateAwnFunction((int) auxArrow.getX1(), (int) auxArrow.getY1(),
						(int) auxArrow.getX2(), (int) auxArrow.getY2(), 50));
			}
			auxArrow.updateGraphicSymbolsPosition();
		}
	}

	public void reset() {
		initialState = null;
	}

	public void notifyPanelNewSymbol(String value, int x, int y) {
		jPanelAutomate.appendSymbol(value, x, y);
	}

	public void setjPanelAutomate(JPanelAutomate jPanelAutomate) {
		this.jPanelAutomate = jPanelAutomate;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public State getInitialState() {
		return initialState;
	}

	public void setInitialState(State initialState) {
		this.initialState = initialState;
	}

	public StateFigure getInitialStateFigure() {
		return initialStateFigure;
	}

	public Arrow getArrow() {
		return arrow;
	}

	public GraphicSymbol appendSymbol(GraphicSymbol symbol) {
		arrow.appendSymbol(symbol);
		return symbol;
	}

}
