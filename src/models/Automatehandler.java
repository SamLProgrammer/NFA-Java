package models;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import view.JPanelAutomate;

public class Automatehandler {

	private ArrayList<String> symbolsList;
	private ArrayList<State> statesList;
	private State initialState;
	private JPanelAutomate jpanelAutoamte; //horrible pero no hay tiempo

	public Automatehandler() {
		symbolsList = new ArrayList<String>();
		statesList = new ArrayList<State>();
	}

	// [q1]
	public boolean belowValidateWord(String word, ArrayList<State> stack) {
		boolean isValidate = false;
		int recent = 0;

		int previous = 1;

		int index = 0;

		while (index < word.length()) {
			while (previous > 0) {
				State state = pop(stack);
				ArrayList<State> possibleStates = state.findPossibleStates(word.charAt(index));
				if (possibleStates.size() > 0) {
					recent += appendToStack(stack, possibleStates); // pasa los estados de un lado al otro
				}
				previous--;
			}
			previous = recent;
			recent = 0;
			index++;
		}
		String stringFinals = "";
		for (State state : stack) {
			stringFinals += state.getValue() + "\n";
			if (state.isFinal()) {
				isValidate = true;
			}
		}
		
		if(isValidate) {
			JOptionPane.showMessageDialog(null, "La palabra es valida, teniendo en cuenta los estados finales. " + stringFinals);
		}else {
			JOptionPane.showMessageDialog(null, "La palabra no es valida, teniendo en cuenta los estados finales. " + stringFinals);
		}
		return isValidate;
	}

	private int appendToStack(ArrayList<State> stack, ArrayList<State> possibleStates) {
		int insertions = 0;
		for (State state : possibleStates) {
			stack.add(state);
			insertions++;
		}
		return insertions;
	}

	private State pop(ArrayList<State> states) {
		State state = states.get(0);
		states.remove(0);
		System.out.println("state : " + state.getValue());
		return state;
	}

	public boolean validateWord(String word) {
		ArrayList<State> stack = new ArrayList<State>();
		stack.add(initialState);
		return belowValidateWord(word, stack);
	}

	public void assignAlias() {
		int counter = 65;
		// falta un FOR para concatenar cuando se acaben las letras del alphabeto AA AB
		// AC
		for (State state : statesList) {
			state.setNonTerminal("" + (char) counter);
			System.out.println(state.getNonTerminal());
			counter++;
		}
	}

	private ArrayList<Production> getProductions() {
		ArrayList<Production> productionsList = new ArrayList<Production>();
		assignAlias();
		scanProductions(productionsList, initialState);
		showArray(productionsList);
		return productionsList;
	}

	private void scanProductions(ArrayList<Production> productionsList, State state) {
		state.setMarked(true);
		for (Transition transition : state.getTransitions()) {
			productionsList.add(new Production(state.getNonTerminal(),
					transition.getValue() + transition.getDestiny().getNonTerminal()));
			if (!transition.getDestiny().isMarked()) {
				scanProductions(productionsList, transition.getDestiny());
			}
		}
	}

	private void showArray(ArrayList<Production> arrayList) {
		System.out.println("===========");
		for (Production production : arrayList) {
			System.out.println(production.toString());
		}
		System.out.println("===========");
	}

	private void unmarkStates() {
		for (State state : statesList) {
			state.setMarked(false);
		}
	}

	private String generateTerminalsForm() {
		String format = "Terminals {";
		for (String symbol : symbolsList) {
			format += symbol + ", ";
		}
		return format + "}\n";
	}

	private String generateNonTerminalsForm() {
		String format = "NON-Terminals {";
		for (State state : statesList) {
			format += state.getNonTerminal() + ", ";
		}
		return format + "}\n";
	}

	private String generateAlphabetForm() {
		String alphabet = "Alphbet {";
		for (State state : statesList) {
			alphabet += state.getNonTerminal() + ", ";
		}
		
		for (String string : symbolsList) {
			alphabet += string + ", ";
		}
		return alphabet + "}\n";
	}

	public String generateGrammarForm() {
		ArrayList<Production> productionsList = getProductions();
		unmarkStates();
		String productionsForm = "Productions {";
		for (Production production : productionsList) {
			productionsForm += production.toString() + "\n";
		}
		return  generateAlphabetForm() + generateNonTerminalsForm() + generateTerminalsForm() + "Initial Symbol : {" + initialState.getNonTerminal() + "}\n" + productionsForm + " }";		
	}

	public void setInitialState(State initialState) {
		this.initialState = initialState;
	}

	public void addSymbol(String value) {
		if (!symbolExists(value)) {
			symbolsList.add(value);
		}
	}

	private boolean symbolExists(String symbol) {
		boolean flag = false;
		for (String string : symbolsList) {
			if (string.equals(symbol)) {
				flag = true;
			}
		}
		return flag;
	}

	public void addState(State state) {
		statesList.add(state);
	}

	public void setJpanelAutoamte(JPanelAutomate jpanelAutoamte) {
		this.jpanelAutoamte = jpanelAutoamte;
	}
}
