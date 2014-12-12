package mygame;

public class NoCoinState extends ConcreteSubject implements State {

    GumballMachine gumballMachine;

    public NoCoinState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void doAction(String eventName) {
        if (!eventName.equals("TurnCrank")) {
            gumballMachine.setState(gumballMachine.getHasCoinState());
            super.setState(eventName);
        }
        else
        {
            System.out.println("No coin, no gumball");
        }
        
    }
}
