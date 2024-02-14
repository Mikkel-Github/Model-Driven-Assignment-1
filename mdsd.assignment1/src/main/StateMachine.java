package main;

import main.metamodel.Machine;

public class StateMachine {
	Machine machine = new Machine();

	public Machine build() {
		return machine;
	}

	public StateMachine state(String string) {
		machine.addState(string);
		return this;
	}

	public StateMachine initial() {
		machine.setInitialState();
		return this;
	}

	public StateMachine when(String event) {
		machine.setEvent(event);
		return this;
	}

	public StateMachine to(String toState) {
		machine.setTarget(toState);
		return this;
	}

	public StateMachine integer(String name) {
		machine.addInteger(name);
		return this;
	}

	public StateMachine set(String name, int value) {
		machine.setOperation(name, value);
		return this;
	}

	public StateMachine increment(String name) {
		machine.setIncrementOperation(name);
		return this;
	}

	public StateMachine decrement(String name) {
		machine.setDecrementOperation(name);
		return this;
	}

	public StateMachine ifEquals(String string, int i) {
		machine.setEqualCondition(string, i);
		return this;
	}

	public StateMachine ifGreaterThan(String string, int i) {
		machine.setGreaterCondition(string, i);
		return this;
	}

	public StateMachine ifLessThan(String string, int i) {
		machine.setLessCondition(string, i);
		return this;
	}

}
