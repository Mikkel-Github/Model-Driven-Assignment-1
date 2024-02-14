package main.metamodel;

import java.util.ArrayList;
import java.util.List;

public class Machine {
	class IntegerObj {
		private String name;
		private int value;
		
		public IntegerObj(String name) {
			this.name = name;
			this.value = 0;
		}
		
		public String getName() { return name; }
		
		public void setValue(int value) {
			this.value = value;
		}
		
		public int getValue() { return value; }
		
		public void Increment() { this.value++; }
		public void Decrement() { this.value--; }
	}
	
	private List<State> states = new ArrayList<State>();
	private int initialState = 0;
	private List<IntegerObj> integers = new ArrayList<IntegerObj>();
	
	private State currentState;
	
	public State getCurrentState() { return currentState; }
	
	public void addState(String name) {
		State newState = new State(name);
		states.add(newState);
		if(currentState == null) currentState = newState;
	}
	
	public List<State> getStates() {
		return states;
	}
	
	public void setInitialState() {
		initialState = states.size() - 1;
		currentState = states.get(initialState);
	}

	public State getInitialState() {
		return states.get(initialState);
	}

	public State getState(String string) {
		for (State state : states) {
			if(state.getName() == string) return state;
		}
		return null;
	}
	
	private State getLatestState() { return states.get(states.size() - 1); }
	
	public void setEvent(String event) {
		getLatestState().addTransition(this, event);
	}

	public void setTarget(String target) {
		getLatestState().setTransitionTarget(target);
	}
	
	// Variables
	public int numberOfIntegers() {
		return integers.size();
	}

	public boolean hasInteger(String name) {
		if(getInt(name) != null) return true;
		return false;
	}
	
	public IntegerObj getInt(String name) {
		for (IntegerObj in : integers) {
			if(in.getName() == name) return in;
		}
		return null;
	}
	
	public int getInteger(String name) {
		return getInt(name).getValue();
	}
	
	// Operation
	public void setOperation(String name, int value) {
		getLatestState().setTransitionOperation(name, value);
	}
	
	public void setIncrementOperation(String name) {
		getLatestState().setIncrementTransitionOperation(name);
	}
	
	public void setDecrementOperation(String name) {
		getLatestState().setDecrementTransitionOperation(name);
	}
	
	public void addInteger(String name) {
		if (hasInteger(name)) return;
		integers.add(new IntegerObj(name));
	}
	
	// Condition
	public void setEqualCondition(String name, int value) {
		getLatestState().setTransitionEqualCondition(name, value);
	}
	
	public void setGreaterCondition(String name, int value) {
		getLatestState().setTransitionGreaterCondition(name, value);
	}
	
	public void setLessCondition(String name, int value) {
		getLatestState().setTransitionLessCondition(name, value);
	}
	
	public void processEvent(String event) {
		List<Transition> conditionalTransitions = new ArrayList<Transition>();
		Transition mainTransition = null;
		for (Transition t : currentState.getTransitions()) {
			if(t.getEvent() == event) {
				if(t.isConditional()) conditionalTransitions.add(t);
				else {
					if(mainTransition == null) mainTransition = t;
				}
			}
		}
		
		for (Transition t : conditionalTransitions) {
			if(t.canTransition()) {
				currentState = t.getTarget();
				return;
			}
		}
		
		// If none of the conditional transitions are fulfilled, pick the main transition
		if(mainTransition != null) currentState = mainTransition.getTarget();
	}
}

