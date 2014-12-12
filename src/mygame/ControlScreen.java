/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.RenderManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import java.util.Timer;


public class ControlScreen extends AbstractAppState implements ScreenController {
    
    SimpleApplication rootApp;
    Nifty nifty;
    
    public void initialize(AppStateManager stateManager,Application app ){
        
        super.initialize(stateManager, app);
        rootApp= (SimpleApplication)app;
        NiftyJmeDisplay controlscreen = new NiftyJmeDisplay(app.getAssetManager(),
                app.getInputManager(), app.getAudioRenderer(), app.getGuiViewPort());
        
        nifty = controlscreen.getNifty();
         
        nifty.fromXml("Interface/ControlScreen.xml", "controls",this);
        
        app.getGuiViewPort().addProcessor(controlscreen);
        
        
    }
    
   
    
    public void next() {
     
       
      this.rootApp.getStateManager().detach(this);
        nifty.exit();
       Main.cr=1;
    }
    

    public void cleanup() {
     
    }
    
    public void onStartScreen() {
       
    }
    
    public void onEndScreen() {
       
    }
    
    public void bind(Nifty nifty, Screen screen)
    {}
    
    
    
}
