package mygame;

public class SoldState extends ConcreteSubject implements State {

    public SoldState(GumballMachine gumballMachine) {
    }

    public void doAction(String eventName) {
        super.setState(eventName);
    }
}
