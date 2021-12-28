package models;

public class Transition {
	private State origin;
	private State destiny;
	private String transitionValue;
	
	public Transition(State origin, State destiny, String transitionValue) {
		this.origin = origin;
		this.destiny = destiny;
		this.transitionValue = transitionValue;
	}
	
	public Transition(State origin, State destiny) {
		this.origin = origin;
		this.destiny = destiny;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public State getOrigin() {
		return origin;
	}

	public void setOrigin(State origin) {
		this.origin = origin;
	}

	public State getDestiny() {
		return destiny;
	}

	public void setDestiny(State destiny) {
		this.destiny = destiny;
	}

	public String getValue() {
		return transitionValue;
	}

	public void setValue(String transitionValue) {
		this.transitionValue = transitionValue;
	}
	
	
	
	
	
}
