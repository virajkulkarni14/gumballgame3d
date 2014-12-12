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


public class MainMenu extends AbstractAppState implements ScreenController {
    
    SimpleApplication rootApp;
    
    public void initialize(AppStateManager stateManager,Application app ){
        
        super.initialize(stateManager, app);
        rootApp= (SimpleApplication)app;
        NiftyJmeDisplay menuscreen = new NiftyJmeDisplay(app.getAssetManager(),
                app.getInputManager(), app.getAudioRenderer(), app.getGuiViewPort());
        
        Nifty nifty = menuscreen.getNifty();
         
        nifty.fromXml("Interface/mainMenu.xml", "start",this);
        
        app.getGuiViewPort().addProcessor(menuscreen);
        
        
    }
    
    public void startGame(){
       // this.rootApp.getStateManager().attach(rootApp);
        this.rootApp.getStateManager().detach(this);
        
    }
    
    public void exitGame() {
        rootApp.stop();
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
