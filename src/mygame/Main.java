package mygame;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.app.SimpleApplication;
import com.jme3.audio.AudioNode;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.font.Rectangle;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.Spatial.CullHint;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Quad;
import java.nio.channels.Channel;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends SimpleApplication implements AnimEventListener{
 
    public static Main app = new Main();
    public static void main(String[] args) {
        //MainHandler app = new MainHandler();
        app.start();
    }
    protected static Spatial animatedGumball, gumballMachine, redGumball, alien, alien1, dollPicker, dime, nickel, quarter, fakeQuarter,nickel2, quarter2, fakeQuarter2;
    protected static Spatial blue,green, yellow,gb;
    Boolean isRunning=true;
    public static int numberOfGumballs = 100;
    public static int amount,change;
    public static int f=0 , af=0;
    public static boolean alienflag=false, pickerflag=false;
    private Spatial sceneModel;
    private Spatial mySky;
    private AnimControl control;
    private AnimChannel channel;
    public static Quad q,q2;
    public static Geometry g,g2;
    public static BitmapFont fnt,fnt2 ;
    public static BitmapText txt,txt2;
    public static AudioNode audio_coin, audio_crank, audio_fake, audio_gumball;
    private AudioNode audio_nature;
    public static BitmapText Quarter;
    
    MainMenu m= new MainMenu();
    ControlScreen contsc = new ControlScreen();
    Credits credits = new Credits();
    
    public static int cr=0,cs=0,mf=0;
       
    @Override
    public void simpleInitApp() {
        
         flyCam.setEnabled(false );
        stateManager.attach(m);
        
      
        setDisplayFps(false);       // to hide the FPS
        setDisplayStatView(false);
        Vector3f v1 = new Vector3f(0, 0, 30);
        cam.setLocation(v1);
        flyCam.setMoveSpeed(100.545f);

        initScene();
        initSky();
        initLight();
        GumballMachine gm = new GumballMachine();
        
        gumballMachine = assetManager.loadModel("Models/Gumballmachine.j3o");
        gumballMachine.setLocalTranslation(0.0f, -5.0f, 2.0f);
        rootNode.attachChild(gumballMachine);        
        
        alien = assetManager.loadModel("Models/alinen_high.obj");
        Material mat_default = new Material( 
            assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");
        alien.setMaterial(mat_default);
        alien.rotate(5.0f, 0.0f, 0.0f);
        alien.setLocalTranslation(-10.0f, 5.0f, -2.0f);
        
        dollPicker = assetManager.loadModel("Models/Dolly_high.obj");
        dollPicker.setMaterial(mat_default);
        dollPicker.rotate(5.0f, 0.0f, 0.0f);
        dollPicker.setLocalTranslation(-12.0f, -5.0f, -2.0f);
        rootNode.attachChild(dollPicker);
        
        dime = assetManager.loadModel("Models/dime.j3o");
        dime.rotate(5.0f, 0.0f, 0.0f);
        dime.setLocalTranslation(10.0f, -1.0f, -2.0f);
        rootNode.attachChild(dime);
        
        nickel = assetManager.loadModel("Models/nickel.j3o");
        nickel.rotate(5.0f, 0.0f, 0.0f);
        nickel.setLocalTranslation(10.0f, -4.0f, -2.0f);
        rootNode.attachChild(nickel);
        
        quarter = assetManager.loadModel("Models/quarter.j3o");
        quarter.rotate(5.0f, 0.0f, 0.0f);
        quarter.setLocalTranslation(10.0f, -7.0f, -2.0f);
        
        
        rootNode.attachChild(quarter);
        
        fakeQuarter = assetManager.loadModel("Models/fakequarter.j3o");
        fakeQuarter.rotate(5.0f, 0.0f, 0.0f);
        fakeQuarter.setLocalTranslation(10.0f, -10.0f, -2.0f);
        rootNode.attachChild(fakeQuarter);
        
        nickel2 = assetManager.loadModel("Models/fakequarter.j3o");
        nickel2.rotate(5.0f, 0.0f, 0.0f);
        nickel2.setLocalTranslation(18.0f, -5.0f, -8.0f);
        rootNode.attachChild(nickel2);
        
        quarter2 = assetManager.loadModel("Models/nickel.j3o");
        quarter2.rotate(5.0f, 0.0f, 0.0f);
        quarter2.setLocalTranslation(18.0f, -9.0f, -8.0f);
        rootNode.attachChild(quarter2);
        
        fakeQuarter2 = assetManager.loadModel("Models/quarter.j3o");
        fakeQuarter2.rotate(5.0f, 0.0f, 0.0f);
        fakeQuarter2.setLocalTranslation(18.0f, 2.0f, -8.0f);
        rootNode.attachChild(fakeQuarter2);
        
        // Display a line of text with a default font
        guiNode.detachAllChildren();
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        Quarter = new BitmapText(guiFont, false);
        Quarter.setSize(guiFont.getCharSet().getRenderedSize());
        Quarter.setText("Lets do as follows to get the gumball :) \n Press 1 - to insert a Nickel\n Press 2 - to insert a Dime\n Press 3 - to insert a Quarter\n Press 4 - It's a Fake quarter !\n Press Space to turn the crank.");
        Quarter.setLocalTranslation(10, Quarter.getLineHeight()+ 650, 0);
        if(mf==0 && cr==0 && cs==0)
        guiNode.attachChild(Quarter);
        
        
        // You must add a light to make the model visible
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
        rootNode.addLight(sun);
        
        //For Amount less message code
        Vector3f v = new Vector3f(5, 1, 2f);
        q = new Quad(4, 1);
        g = new Geometry("quad",q);
        g.setLocalTranslation(-15.0f, 0.0f, -2.0f); //-12.0f, -5.0f, -2.0f
        g.setMaterial(Main.app.getAssetManager().loadMaterial("Common/Materials/WhiteColor.j3m"));
        g.scale(1.7f, 1.5f, 1.5f);
        
        fnt = Main.app.getAssetManager().loadFont("Interface/Fonts/Default.fnt");
        txt = new BitmapText(fnt, false);
        txt.setBox(new Rectangle(0, 0, 6, 3) );
        txt.setLocalTranslation(-15.0f, 1.0f, -2.0f);
        txt.setColor(ColorRGBA.Black);
        txt.scale(1.5f, 1.5f, 1.5f);
        
        txt.setQueueBucket(RenderQueue.Bucket.Transparent);
        txt.setSize( 0.5f );
        
        //For fake quarter message code
        Vector3f v2 = new Vector3f(5, 1, 2f);
        q2 = new Quad(4, 1);
        g2 = new Geometry("quad",q2);
        g2.setLocalTranslation(-15.0f, 0.0f, -2.0f); //-12.0f, -5.0f, -2.0f
        g2.setMaterial(Main.app.getAssetManager().loadMaterial("Common/Materials/WhiteColor.j3m"));
        g2.scale(1.7f, 1.5f, 1.5f);
        
        fnt2 = Main.app.getAssetManager().loadFont("Interface/Fonts/Default.fnt");
        txt2 = new BitmapText(fnt2, false);
        txt2.setBox(new Rectangle(0, 0, 6, 3) );
        txt2.setLocalTranslation(-15.0f, 1.0f, -2.0f);
        txt2.setColor(ColorRGBA.Black);
        txt2.scale(1.5f, 1.5f, 1.5f);
        
        txt2.setQueueBucket(RenderQueue.Bucket.Transparent);
        txt2.setSize( 0.5f );
        
        initKeys();
        initAudio();
    }
    
     private void initScene()
    {
    sceneModel = assetManager.loadModel("Scenes/Scene.j3o");
    sceneModel.move(0.0f,-8.0f,1.0f);
    rootNode.attachChild(sceneModel);
    }
    
    private void initSky()
    {
    mySky = assetManager.loadModel("Scenes/mySky.j3o");
    rootNode.attachChild(mySky);
    }



    private void initLight()
    {
            /** A white ambient light source. */ 
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(ColorRGBA.White);
        rootNode.addLight(ambient); 
    } 
    
    @Override
    public void simpleUpdate(float tpf) {
        
        if(cs==1)
        {
            stateManager.detach(m);
            mf=0;
            stateManager.attach(contsc);
            
            cs=0;
        }
        
        if(cr==1)
        {
            stateManager.detach(contsc);
            stateManager.attach(credits);
            
            cr=0;
        }
        
        listener.setLocation(cam.getLocation());
        listener.setRotation(cam.getRotation());
        // make the player rotate:
        alien.rotate(0, 0, 1*tpf); 
        dime.rotate(0, 0, 2*tpf); 
        nickel.rotate(0, 0, -1*tpf); 
        quarter.rotate(0, 0, 4*tpf); 
        fakeQuarter.rotate(0, 0, 3*tpf); 
        nickel2.rotate(0, 0, -2*tpf); 
        quarter2.rotate(0, 0, -4*tpf); 
        fakeQuarter2.rotate(0, 0, -3*tpf);
        guiFont = getAssetManager().loadFont("Interface/Fonts/Default.fnt");
        BitmapText text = new BitmapText(guiFont, false);
        text.setSize(guiFont.getCharSet().getRenderedSize());
        text.setLocalTranslation(450, text.getLineHeight()+ 600, 0);
        
        BitmapText text2 = new BitmapText(guiFont, false);
        text2.setSize(guiFont.getCharSet().getRenderedSize());
        text2.setLocalTranslation(550, text.getLineHeight()+ 500, 0);
        
        if(alienflag==true)
            rootNode.detachChild(alien);
        
        if(pickerflag==true)
        {
            //detach text thing on picker
            rootNode.detachChild(g);
            rootNode.detachChild(txt);
            rootNode.detachChild(g2);
            rootNode.detachChild(txt2);
        }
        
        if(f==1){
            if(gb!= null)
                rootNode.detachChild(gb);
        alienflag=false;       
        gb = assetManager.loadModel("Models/bluegumball.j3o");
        gb.rotate(5.0f, 0.0f, 0.0f);
        gb.setLocalTranslation(1f, -5.0f, 3.5f);
        rootNode.attachChild(gb);
        gb.rotate(1*tpf, 1*tpf, 1*tpf);
        rootNode.attachChild(alien);
        f=0;
             
        
        guiNode.detachAllChildren();
        if(mf==0 && cr==0 && cs==0)
        guiNode.attachChild(Quarter);
        text.setText("Blue Gumball!\tChange is " + change + "\tGumballs left: " + numberOfGumballs);        
        guiNode.attachChild(text);
        guiNode.attachChild(text);
        
        }
        
        if(f==2){
            if(gb!= null)
                    rootNode.detachChild(gb);
        alienflag=false;
               
        gb = assetManager.loadModel("Models/greengumball.j3o");
        gb.rotate(5.0f, 0.0f, 0.0f);
        gb.setLocalTranslation(1f, -5.0f, 3.5f);
        rootNode.attachChild(gb);
        gb.rotate(1*tpf, 1*tpf, 1*tpf);
        f=0;
          
         guiNode.detachAllChildren();
         if(mf==0 && cr==0 && cs==0)
         guiNode.attachChild(Quarter);
        text.setText("Green Gumball!\tChange is " + change + "\tGumballs left: " + numberOfGumballs);        
        guiNode.attachChild(text);
        rootNode.attachChild(alien);   
        alien.setCullHint(CullHint.Never);
        }
        
        if(f==3){
        
            if(gb!= null)
                rootNode.detachChild(gb);
            alienflag=false;
            
        gb = assetManager.loadModel("Models/redgumball.j3o");
        gb.rotate(5.0f, 0.0f, 0.0f);
        gb.setLocalTranslation(1f, -5.0f, 3.5f);
        rootNode.attachChild(gb);
        gb.rotate(1*tpf, 1*tpf, 1*tpf);
        f=0;
        
        guiNode.detachAllChildren();
        if(mf==0 && cr==0 && cs==0)
        guiNode.attachChild(Quarter);
        text.setText("Red Gumball!\tChange is " + change + "\tGumballs left: " + numberOfGumballs);        
        guiNode.attachChild(text);
            
        rootNode.attachChild(alien);
        
        }
        
        if(f==4){
            if(gb!= null)
                rootNode.detachChild(gb);
            alienflag=false;
            
            gb = assetManager.loadModel("Models/yellogumball.j3o");
            gb.rotate(5.0f, 0.0f, 0.0f);
            gb.rotate(5.0f, 5.0f, 5.0f);
            gb.setLocalTranslation(1f, -5.0f, 3.5f);
            rootNode.attachChild(gb);
            gb.rotate(1*tpf, 1*tpf, 1*tpf);
            f=0;
            guiNode.detachAllChildren();
            if(mf==0 && cr==0 && cs==0)
            guiNode.attachChild(Quarter);
            text.setText("Yellow Gumball!\tChange is " + change + "\tGumballs left: " + numberOfGumballs);        
            guiNode.attachChild(text);

            rootNode.attachChild(alien);        
        
        }
        
        
        if(af==1)
        {
            rootNode.detachChild(alien);
          guiNode.detachAllChildren();
          if(mf==0 && cr==0 && cs==0)
          guiNode.attachChild(Quarter);
          text2.setText("Amount in Gumball Machine = " + amount);        
          guiNode.attachChild(text2);
          af=0;
        }
        
    }
    
    private void initAudio() {
    /* gun shot sound is to be triggered by a mouse click. */
    audio_coin = new AudioNode(assetManager, "Sound/Coin.wav", false);
    audio_coin.setPositional(false);
    audio_coin.setLooping(false);
    audio_coin.setVolume(2);
    rootNode.attachChild(audio_coin);
    
    audio_crank = new AudioNode(assetManager, "Sound/Coin.wav", false);
    audio_crank.setPositional(false);
    audio_crank.setLooping(false);
    audio_crank.setVolume(2);
    rootNode.attachChild(audio_crank);
    
    audio_gumball = new AudioNode(assetManager, "Sound/gumball.wav", false);
    audio_gumball.setPositional(false);
    audio_gumball.setLooping(false);
    audio_gumball.setVolume(2);
    rootNode.attachChild(audio_gumball);
    
    audio_fake = new AudioNode(assetManager, "Sound/Not.wav", false);
    audio_fake.setPositional(false);
    audio_fake.setLooping(false);
    audio_fake.setVolume(2);
    rootNode.attachChild(audio_fake);
 
    /* nature sound - keeps playing in a loop. */
    audio_nature = new AudioNode(assetManager, "Sound/background.wav", true);
    audio_nature.setLooping(true);  // activate continuous playing
    audio_nature.setPositional(true);   
    audio_nature.setVolume(3);
    rootNode.attachChild(audio_nature);
    audio_nature.play(); // play continuously!
  }
    
    
    private void initKeys() {
    // You can map one or several inputs to one named action
    inputManager.addMapping("Pause",  new KeyTrigger(KeyInput.KEY_P));
    inputManager.addMapping("Left",   new KeyTrigger(KeyInput.KEY_J));
    inputManager.addMapping("Right",  new KeyTrigger(KeyInput.KEY_K));
    inputManager.addMapping("Up",  new KeyTrigger(KeyInput.KEY_I));
    inputManager.addMapping("Down",  new KeyTrigger(KeyInput.KEY_M));
    inputManager.addMapping("Rotate", new KeyTrigger(KeyInput.KEY_SPACE),
                                      new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
    // Add the names to the action listener.
    inputManager.addListener(actionListener,"Pause");
    inputManager.addListener(analogListener,"Left", "Right", "Up", "Down", "Rotate");
 
  }
 
  private ActionListener actionListener = new ActionListener() {
    public void onAction(String name, boolean keyPressed, float tpf) {
      if (name.equals("Pause") && !keyPressed) {
        isRunning = !isRunning;
      }
    }
  };
 
  private AnalogListener analogListener = new AnalogListener() {
    public void onAnalog(String name, float value, float tpf) {
      if (isRunning) {
        if (name.equals("Rotate")) {
          dollPicker.rotate(0, 0, value*speed);
        }
        if (name.equals("Right")) {
          Vector3f v = dollPicker.getLocalTranslation();
          dollPicker.setLocalTranslation(v.x + value*speed, v.y, v.z);
        }
        if (name.equals("Left")) {
          Vector3f v = dollPicker.getLocalTranslation();
          dollPicker.setLocalTranslation(v.x - value*speed, v.y, v.z);
        }
        if (name.equals("Up")) {
          Vector3f v = dollPicker.getLocalTranslation();
          dollPicker.setLocalTranslation(v.x, v.y + value*speed, v.z);
        }
        if (name.equals("Down")) {
          Vector3f v = dollPicker.getLocalTranslation();
          dollPicker.setLocalTranslation(v.x, v.y - value*speed, v.z);
        }
      } else {
        System.out.println("Press P to unpause.");
      }
    }
  };

    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
   
}