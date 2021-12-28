package models;

import java.util.ArrayList;

public class State {
	private String value;
	private String nonTerminal;
	private ArrayList<Transition> transitions;
	private boolean isInitial;
	private boolean isFinal;
	private boolean marked;
	
	public State(String value) {
		this.value = value;
		initTransitionsList();
	}

	private void initTransitionsList() {
		this.transitions = new ArrayList<Transition>();
	}
	
	public void addTransition(Transition transition) {
		transitions.add(transition);
	}
	

	public ArrayList<State> findPossibleStates(char charAt) {
		ArrayList<State> possibleStates = new ArrayList<State>();
		for (Transition transition : transitions) {
			if(transition.getValue().equals( charAt + "" )) {
				possibleStates.add(transition.getDestiny());
			}
		}
		showArray(possibleStates);
		return possibleStates;
	}
	
	private void showArray(ArrayList<State> arrayList){
		System.out.println("===========");
		for (State state : arrayList) {
			System.out.print(state.getValue() +", ");
		}
		System.out.println("===========");
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ArrayList<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(ArrayList<Transition> transitions) {
		this.transitions = transitions;
	}

	public boolean isFinalice() {
		return isInitial;
	}

	public void setFinalice(boolean finalice) {
		this.isInitial = finalice;
	}

	public boolean isInitial() {
		return isInitial;
	}

	public void setInitial(boolean isInitial) {
		this.isInitial = isInitial;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}
	
	public boolean isMarked() {
		return marked;
	}
	
	public void setMarked(boolean marked) {
		this.marked = marked;
	}
	
	public void setNonTerminal(String nonTerminal) {
		this.nonTerminal = nonTerminal;
	}
	
	public String getNonTerminal() {
		return nonTerminal;
	}

}
