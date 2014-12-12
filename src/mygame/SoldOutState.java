package mygame;

public class SoldOutState extends ConcreteSubject implements State {

    public SoldOutState(GumballMachine gumballMachine) {
    }

    public void doAction(String eventName) {
        super.setState(eventName);
    }
}