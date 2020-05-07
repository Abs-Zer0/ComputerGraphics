/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.graphics.rt.scene.geometry;

import java.util.Vector;
import my.graphics.rt.scene.Primitive;
import org.apache.commons.math3.geometry.euclidean.threed.Line;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.util.FastMath;

/**
 *
 * @author Абс0лютный Н0ль
 */
public class Spheroid implements Primitive {

    private Vector3D origin;
    private double a, b, c;

    public Spheroid(double a, double b, double c) {
        this(Vector3D.ZERO, a, b, c);
    }

    public Spheroid(Vector3D cent, double a, double b, double c) {
        this.origin = cent;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public Vector<Line> RayIntersect(Line ray) {
        final Line r = ray;
        final Vector3D distOrig = r.getOrigin().subtract(this.origin);
        final double sa = this.a * this.a;
        final double sb = this.b * this.b;
        final double sc = this.c * this.c;

        final double ka = sb * sc * r.getDirection().getX() * r.getDirection().getX()
                + sa * sc * r.getDirection().getY() * r.getDirection().getY()
                + sa * sb * r.getDirection().getZ() * r.getDirection().getZ();
        final double km = sb * sc * distOrig.getX() * r.getDirection().getX()
                + sa * sc * distOrig.getY() * r.getDirection().getY()
                + sa * sc * distOrig.getZ() * r.getDirection().getZ();
        final double kc = sb * sc * distOrig.getX() * distOrig.getX()
                + sa * sc * distOrig.getY() * distOrig.getY()
                + sa * sb * distOrig.getZ() * distOrig.getZ();

        final double D = km * km - ka * kc;
        if (D < 0) {
            return new Vector<>();
        }
        if (D == 0) {
            final Vector<Line> res = new Vector<>();
            final double t = -km / ka;
            if (t > 0) {
                final Vector3D direct = new Vector3D(0, 0, 0);
                final Line point = new Line(r.getOrigin().add(t, r.getDirection()), direct, 1.0e-10);
                res.add(point);
            }

            return res;
        }

        final Vector<Line> res = new Vector<>();
        final double sqrtD = FastMath.sqrt(D);
        final double t1 = (-km - sqrtD) / ka;
        final double t2 = (-km + sqrtD) / ka;
        if (t1 > 0) {
            final Vector3D direct = new Vector3D(0, 0, 0);
            final Line point = new Line(r.getOrigin().add(t1, r.getDirection()), direct, 1.0e-10);
            res.add(point);
        }
        if (t2 > 0) {
            final Vector3D direct = new Vector3D(0, 0, 0);
            final Line point = new Line(r.getOrigin().add(t2, r.getDirection()), direct, 1.0e-10);
            res.add(point);
        }

        return res;
    }

    @Override
    public void SetPosition(Vector3D pos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vector3D GetPosition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void SetRotation(Vector3D pos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vector3D GetRotation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
