package mygame;

public class HasCoinState extends ConcreteSubject implements State {

    public HasCoinState(GumballMachine gumballMachine) {
    }

    @Override
    public void doAction(String eventName) {
        super.setState(eventName);
    }
}