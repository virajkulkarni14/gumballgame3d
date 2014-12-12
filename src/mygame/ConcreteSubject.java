/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import java.util.ArrayList;

/**
 *
 * @author KomalKishor
 */
public class ConcreteSubject implements Subject {

    public String eventName;
    private ArrayList<Observers> observers = new ArrayList<Observers>();

    public void setState(String eventName) {
        this.eventName = eventName;
        notifyObservers();
    }

    public String getState() {
        return eventName;
    }

    public void doAction(String eventName) {
        //no implementation ;
    }

    public void attach(Observers obj) {
        observers.add(obj);
    }

    public void detach(Observers obj) {
        observers.remove(obj);
    }

    public void notifyObservers() {
        for (Observers obj : observers) {
            obj.update(eventName);
        }
    }

    public void showState() {
        System.out.println("Event triggerd: " + eventName);
    }
}
