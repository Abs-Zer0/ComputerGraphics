/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.graphics.rt;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import my.graphics.rt.scene.Node;
import my.graphics.rt.scene.geometry.Spheroid;
import org.apache.commons.math3.geometry.euclidean.threed.Line;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 *
 * @author Абс0лютный Н0ль
 */
public class App {

    public static void main(String[] args) {
        Vector3D p1 = new Vector3D(-1, -1, 0);
        Vector3D p2 = new Vector3D(1, 0, 0);
        Vector3D p3 = new Vector3D(2, 0, 0);
        Vector3D p4 = new Vector3D(1, 1, 0);

        System.out.println("p1<->p2: " + p1.distance(p2)
                + "; ^2: " + p1.distanceSq(p2)
                + "; 1: " + p1.distance1(p2)
                + "; Inf: " + p1.distanceInf(p2));
        System.out.println("p1<->p3: " + p1.distance(p3)
                + "; ^2: " + p1.distanceSq(p3)
                + "; 1: " + p1.distance1(p3)
                + "; Inf: " + p1.distanceInf(p3));
        System.out.println("p1<->p4: " + p1.distance(p4)
                + "; ^2: " + p1.distanceSq(p4)
                + "; 1: " + p1.distance1(p4)
                + "; Inf: " + p1.distanceInf(p4));

        Line l = new Line(new Vector3D(0, 0, 0), new Vector3D(1, 0, 0), 1e-10);
        Spheroid s = new Spheroid(1, 1, 1);
        Vector<Line> vec = s.RayIntersect(l);
        System.out.println(vec.size());
        vec.stream().forEach(action -> System.out.println(action.getOrigin().toString()));
    }
}
