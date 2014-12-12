/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.font.BitmapText;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.scene.Spatial;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;

/**
 *
 * @author KomalKishor
 */
public class RandomPicker extends AbstractAppState implements PickerStratergy {
    
    
    public RandomPicker() {
    }

    public void pick() {
        int lottery = (int) (Math.random() * 4 + 1);
        Main.change = Main.amount - 50;
        Main.amount = 0;
        if (lottery == 1) {
            System.out.println("Red Gumball and change is " + Main.change);
            Main.numberOfGumballs--;
            System.out.println("Gumballs left " + Main.numberOfGumballs);
            Main.audio_gumball.playInstance();
            Main.f=3;
            
        } 
        
        
        else if (lottery == 2) {
        System.out.println("Yellow Gumball and change is " + Main.change);
            
        Main.numberOfGumballs--;
        System.out.println("Gumballs left " + Main.numberOfGumballs);
        Main.audio_gumball.playInstance();
        Main.f=4;
       
        } 
        
        
        else if (lottery == 3) {
            System.out.println("Blue Gumball and change is " + Main.change);
            Main.numberOfGumballs--;
            System.out.println("Gumballs left " + Main.numberOfGumballs);
            Main.audio_gumball.playInstance();
            Main.f=1;
            
        } 
        
        
        
        else if (lottery == 4) {
            System.out.println("Greem Gumball and change is " + Main.change);
            Main.numberOfGumballs--;
            System.out.println("Gumballs left " + Main.numberOfGumballs);
            Main.audio_gumball.playInstance();
            Main.f=2;
           
        }
        
        
    }
    
   
}

