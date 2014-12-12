    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KomalKishor
 */
public class Picker extends HandlerObserver {

    private HandlerObserver successor = null;
    PickerStratergy pickerstratergy1 = new RandomPicker();
    PickerStratergy pickerstratergy2 = new GreenPicker();

    public Picker() {
        super();
    }

//    public void update() {
//        
//	}
    public void handleRequest(String request) {
        //if(randompicker)->pickerstratergy1.pick
        //if(greenpicker)->pickerstratergy2.pick
        if(Main.amount>50)
        {
        int random = (int) (Math.random() * 2 + 1);
        if (random == 1) {
            try {
                pickerstratergy1.pick();
            } catch (Exception ex) {
                Logger.getLogger(Picker.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (random == 2) {
            try {
                pickerstratergy2.pick();
            } catch (Exception ex) {
                Logger.getLogger(Picker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    }

    public void setSuccessor(HandlerObserver next) {
        this.successor = next;
    }
}
