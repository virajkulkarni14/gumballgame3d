/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.font.BitmapText;

/**
 *
 * @author KomalKishor
 */
public class GreenPicker implements PickerStratergy {

    Main m;
    public GreenPicker() {

    }

    public void pick() {
        Main.change = Main.amount - 50;
        Main.amount = 0;
        System.out.println("Green Gumball and change is " + Main.change);
        Main.numberOfGumballs--;
        System.out.println("Gumballs left " + Main.numberOfGumballs);
        Main.audio_gumball.playInstance();
        Main.f=2;
    }
}
