package main.metamodel;

import java.util.ArrayList;
import java.util.List;

public class State {
	String name = "";
	List<Transition> transitions = new ArrayList<Transition>();
	
	public State(String name) {
		this.name = name;
	}
	
	public Object getName() {
		return name;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}
	
	private Transition getLatestTransition() { return transitions.get(transitions.size() - 1); }
	
	public void addTransition(Machine parentMachine, String event) {
		transitions.add(new Transition(parentMachine, event));
	}
	
	public void setTransitionTarget(String target) {
		getLatestTransition().setTarget(target);
	}

	public Transition getTransitionByEvent(String event) {
		for (Transition transition : transitions) {
			if(transition.getEvent() == event) return transition;
		}
		
		return null;
	}
	
	// Operation
	public void setTransitionOperation(String operation, int value) {
		getLatestTransition().setOperation(operation, value);
	}
	
	public void setIncrementTransitionOperation(String operation) {
		getLatestTransition().setIncrementOperation(operation);
	}
	
	public void setDecrementTransitionOperation(String operation) {
		getLatestTransition().setDecrementOperation(operation);
	}
	
	// Condition
	public void setTransitionEqualCondition(String variableName, int value) {
		getLatestTransition().setEqualCondition(variableName, value);
	}
	public void setTransitionGreaterCondition(String variableName, int value) {
		getLatestTransition().setGreaterCondition(variableName, value);
	}
	public void setTransitionLessCondition(String variableName, int value) {
		getLatestTransition().setLessCondition(variableName, value);
	}
}
