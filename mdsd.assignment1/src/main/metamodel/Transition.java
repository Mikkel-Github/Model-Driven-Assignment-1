package main.metamodel;

public class Transition{
	enum OperationType {
		SET,
		INCREMENT,
		DECREMENT
	}
	
	class Operation {
		private Machine parentMachine;
		private String intName;
		private int value;
		
		private OperationType operationType;
		
		public Operation(Machine parentMachine, String name, OperationType operationType) {
			this.parentMachine = parentMachine;
			this.intName = name;
			this.operationType = operationType;
		}
		
		public void SetToValue(int value) { this.value = value; }
		
		public String getName() { return this.intName; }
		
		public int getValue() { return parentMachine.getInt(this.intName).getValue(); }
		
		public boolean hasOperationType(OperationType operationType) { return this.operationType == operationType; }
		
		public void Increment() { parentMachine.getInt(this.intName).Increment(); }
		
		public void Decrement() { parentMachine.getInt(this.intName).Decrement(); }
		
		public void SetValue(String name, int value) { parentMachine.getInt(name).setValue(value); }
		
		public void RunOperation() {
			switch(operationType) {
			case SET:
				SetValue(intName, value);
				break;
			case INCREMENT:
				Increment();
				break;
			case DECREMENT:
				Decrement();
				break;
			default:
				break;
			}	
		}
	}
	
	enum ConditionType {
		EQUALS,
		GREATERTHAN,
		LESSTHAN
	}
	
	class Condition {
		private Machine parentMachine;
		private String valueName;
		private int value;
		private ConditionType conditionType;
		
		public Condition(Machine parentMachine, String name, int value, ConditionType conditionType) {
			this.parentMachine = parentMachine;
			this.valueName = name;
			this.value = value;
			this.conditionType = conditionType;
		}
		
		public String getName() { return valueName; }
		
		public int getValue() { return value; }
		
		public boolean isTrue() {
			switch (conditionType) {
			case EQUALS:
				return parentMachine.getInt(valueName).getValue() == value;
			case GREATERTHAN:
				return parentMachine.getInt(valueName).getValue() > value;
			case LESSTHAN:
				return parentMachine.getInt(valueName).getValue() < value;
			default:
				return false;
			}
		}
	}
	
	private Machine parentMachine;
	
	String event;
	String target;
	
	Operation operation;
	
	Condition condition;	
	
	public Transition(Machine parentMachine, String event) {
		this.parentMachine = parentMachine;
		this.event = event;
	}
	
	public void setEvent(String event) {
		this.event = event; 
	}
	
	public Object getEvent() {
		return this.event;
	}
	
	public void setTarget(String target) {
		this.target = target;
	}

	public State getTarget() {
		// TODO : Probably want to run the operation here - Mikkel
		if(operation != null) operation.RunOperation();
		return parentMachine.getState(target);
	}

	public void setOperation(String name, int value) {
		operation = new Operation(parentMachine, name, OperationType.SET);
		operation.SetToValue(value);
	}
	
	public void setIncrementOperation(String name) {
		operation = new Operation(parentMachine, name, OperationType.INCREMENT);
	}
	
	public void setDecrementOperation(String name) {
		operation = new Operation(parentMachine, name, OperationType.DECREMENT);
	}
	
	public boolean hasSetOperation() {
		return operation.hasOperationType(OperationType.SET);
	}

	public boolean hasIncrementOperation() {
		return operation.hasOperationType(OperationType.INCREMENT);
	}

	public boolean hasDecrementOperation() {
		return operation.hasOperationType(OperationType.DECREMENT);
	}

	public Object getOperationVariableName() {
		return operation.getName();
	}
	
	public void setEqualCondition(String name, int value) {
		condition = new Condition(parentMachine, name, value, ConditionType.EQUALS);
	}
	
	public void setGreaterCondition(String name, int value) {
		condition = new Condition(parentMachine, name, value, ConditionType.GREATERTHAN);
	}
	
	public void setLessCondition(String name, int value) {
		condition = new Condition(parentMachine, name, value, ConditionType.LESSTHAN);
	}

	public boolean canTransition() {
		if(!isConditional()) return true;
		
		return condition.isTrue();
	}
	
	public boolean isConditional() {
		return condition != null;
	}

	public Object getConditionVariableName() {
		return condition.getName();
	}

	public Integer getConditionComparedValue() {
		return condition.getValue();
	}

	public boolean isConditionEqual() {
		return condition.isTrue();
	}

	public boolean isConditionGreaterThan() {
		return condition.isTrue();
	}

	public boolean isConditionLessThan() {
		return condition.isTrue();
	}

	public boolean hasOperation() {
		return operation != null;
	}

}
