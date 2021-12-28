package view;

public class ArrowHandler {
	private Arrow arrow;
	private StateFigure destinyState;
	private StateFigure initialState;
	private boolean isDestiny;
	
	public ArrowHandler(Arrow arrow, boolean isDestiny, StateFigure initialState, StateFigure destinyState) {
		this.arrow = arrow;
		this.isDestiny = isDestiny;
		this.destinyState = destinyState;
		this.initialState = initialState;
	}
	
	public Arrow getArrow() {
		return arrow;
	}
	
	public boolean isDestiny() {
		return isDestiny;
	}
	
	public void setDestinyState(StateFigure destinyState) {
		this.destinyState = destinyState;
	}
	
	public StateFigure getDestinyState() {
		return destinyState;
	}
	
	public StateFigure getInitialState() {
		return initialState;
	}
}
