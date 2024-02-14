package main;

import main.metamodel.Machine;
import main.metamodel.State;

public class MachineInterpreter {
	Machine machine;
    public void run(Machine m) {
    	machine = m;
    }

    public State getCurrentState() {
        return machine.getCurrentState();
    }

    public void processEvent(String event) {
        machine.processEvent(event);
    }

    public int getInteger(String name) {
        return machine.getInteger(name);
    }

}
