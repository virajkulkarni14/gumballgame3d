/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;

/**
 *
 * @author KomalKishor
 */
public class GumballMachine {

    ConcreteSubject soldOutState;
    ConcreteSubject noCoinState;
    ConcreteSubject hasCoinState;
    ConcreteSubject soldState;
    ConcreteSubject state = soldOutState;
    public int count = 0;
    public static boolean fakeq=false;

    public GumballMachine() {
        initKeys();
        soldOutState = new SoldOutState(this);
        noCoinState = new NoCoinState(this);
        hasCoinState = new HasCoinState(this);
        soldState = new SoldState(this);
        if (Main.numberOfGumballs > 0) {
            state = noCoinState;
            HandlerObserver handlerObserver1 = new HandlerObserver(soldOutState);
            HandlerObserver handlerObserver2 = new HandlerObserver(noCoinState);
            HandlerObserver handlerObserver3 = new HandlerObserver(hasCoinState);
            HandlerObserver handlerObserver4 = new HandlerObserver(soldState);
            
            soldOutState.attach(handlerObserver1);
            noCoinState.attach(handlerObserver2);
            hasCoinState.attach(handlerObserver3);
            soldState.attach(handlerObserver4);
        }
    }

    private void initKeys() {
        // You can map one or several inputs to one named action
        Main.app.getInputManager().addMapping("Nickle", new KeyTrigger(KeyInput.KEY_1));
        Main.app.getInputManager().addMapping("Dime", new KeyTrigger(KeyInput.KEY_2));
        Main.app.getInputManager().addMapping("Quater", new KeyTrigger(KeyInput.KEY_3));
        Main.app.getInputManager().addMapping("FakeQuater", new KeyTrigger(KeyInput.KEY_4));
        Main.app.getInputManager().addMapping("TurnCrank", new KeyTrigger(KeyInput.KEY_SPACE));
        // Add the names to the action listener.
        Main.app.getInputManager().addListener(actionListener, "Nickle", "Dime", "Quater", "FakeQuater", "TurnCrank");

    }
    private ActionListener actionListener = new ActionListener() {
        public void onAction(String name, boolean keyPressed, float tpf) {
            if (!keyPressed) {
                    Main.alienflag=true;
                    Main.pickerflag=true;
                    state.doAction(name);
                    if(name=="TurnCrank")
                        Main.audio_crank.playInstance();
                    else if(fakeq==true)
                        Main.audio_fake.playInstance();
                    else
                        Main.audio_coin.playInstance(); // play each instance once!
                }
            }
    };

    void setState(ConcreteSubject state) {
        this.state = state;
    }

    public ConcreteSubject getSoldOutState() {
        return soldOutState;
    }

    public ConcreteSubject getNoCoinState() {
        return noCoinState;
    }

    public ConcreteSubject getHasCoinState() {
        return hasCoinState;
    }

    public ConcreteSubject getSoldState() {
        return soldState;
    }
}
