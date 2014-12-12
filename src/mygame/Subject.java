/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

/**
 *
 * @author KomalKishor
 */
public interface Subject {

    public abstract void attach(Observers obj);

    public abstract void detach(Observers obj);

    public abstract void notifyObservers();
}
