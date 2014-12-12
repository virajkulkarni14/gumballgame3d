/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

/**
 *
 * @author KomalKishor
 */
public class HandlerObserver implements Observers {

    public String observerState;
    public ConcreteSubject subject;
    public ScoreBoard scoreBoard;
    public Picker picker;
    public Inspector inspector;
    
    public HandlerObserver()
            {
                
            }

    public HandlerObserver(ConcreteSubject theSubject) {
        this.subject = theSubject;
        inspector = new Inspector();
        picker = new Picker();
        scoreBoard = new ScoreBoard();

        scoreBoard.setSuccessor(inspector);
        inspector.setSuccessor(picker);
    }

    public void handleRequest(String request) {
        //do nothing
    }

    public void setSuccessor(HandlerObserver next) {
        //do nothing
    }

    public void update(String eventName) {
        // do nothing
        scoreBoard.handleRequest(eventName);
    }

    public void showState() {
        //System.out.println("Observer: " + this.getClass().getName() + " = " + observerState);
    }
}
