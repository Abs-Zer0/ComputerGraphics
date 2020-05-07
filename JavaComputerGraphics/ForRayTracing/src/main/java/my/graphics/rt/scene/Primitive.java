/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.graphics.rt.scene;

import java.util.Vector;
import org.apache.commons.math3.geometry.euclidean.threed.Line;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 *
 * @author Абс0лютный Н0ль
 */
public interface Primitive {
    Vector<Line> RayIntersect(final Line ray);
    
    void SetPosition(final Vector3D pos);
    Vector3D GetPosition();
    
    void SetRotation(final Vector3D pos);
    Vector3D GetRotation();
}
