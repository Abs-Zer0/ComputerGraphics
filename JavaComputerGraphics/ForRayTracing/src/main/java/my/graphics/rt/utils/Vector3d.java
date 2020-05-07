/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.graphics.rt.utils;

/**
 *
 * @author Абс0лютный Н0ль
 */
public class Vector3d extends Vector3<Double> {

    public Vector3d() {
        this(0);
    }

    public Vector3d(double val) {
        super(val);
    }

    public Vector3d(double x, double y, double z) {
        super(x, y, z);
    }

    public Vector3d add(Vector3<? extends Number> val) {
        elementData[0] = x() + val.x().doubleValue();
        elementData[1] = y() + val.y().doubleValue();
        elementData[2] = z() + val.z().doubleValue();

        return this;
    }

    public Vector3d multiply(Number val) {
        elementData[0] = x() * val.doubleValue();
        elementData[1] = y() * val.doubleValue();
        elementData[2] = z() * val.doubleValue();

        return this;
    }

    public double multiply(Vector3<? extends Number> val) {
        double x = x() * val.x().doubleValue();
        double y = y() * val.y().doubleValue();
        double z = z() * val.z().doubleValue();

        return x + y + z;
    }

    public Vector3d normalize() {
        double multi = 1 / lenght();
        elementData[0] = x() * multi;
        elementData[1] = y() * multi;
        elementData[2] = z() * multi;

        return this;
    }
}
