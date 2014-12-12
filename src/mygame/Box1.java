/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 *
 * @author KomalKishor
 */

public class Box1 {
    public Main app;
    public Box1(Main app)
    {
    this.app=app;
    }
     public void initBox1()
    {
       float r = FastMath.DEG_TO_RAD * 45f;
       Vector3f v = new Vector3f(2.0f , 1.0f , 3.0f);
       
        Box b = new Box(1, 1, 1);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);
        geom.setLocalTranslation(v);
        geom.rotate(r, 0.0f, 0.0f);
        app.getRootNode().attachChild(geom);
    }
    
}
