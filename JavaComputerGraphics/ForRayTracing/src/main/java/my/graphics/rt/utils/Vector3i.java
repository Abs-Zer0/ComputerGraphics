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
public class Vector3i extends Vector3<Integer> {

    public Vector3i() {
        this(0);
    }

    public Vector3i(int val) {
        super(val);
    }

    public Vector3i(int x, int y, int z) {
        super(x, y, z);
    }

    public Vector3i add(Vector3<? extends Number> val) {
        elementData[0] = x() + val.x().intValue();
        elementData[1] = y() + val.y().intValue();
        elementData[2] = z() + val.z().intValue();

        return this;
    }

    public Vector3i dot(Vector3<? extends Number> val) {
        return add(toVec3i(val).reverse());
    }

    public Vector3i multiply(Number val) {
        elementData[0] = x() * val.intValue();
        elementData[1] = y() * val.intValue();
        elementData[2] = z() * val.intValue();

        return this;
    }

    public double multiply(Vector3<? extends Number> val) {
        double x = x() * val.x().intValue();
        double y = y() * val.y().intValue();
        double z = z() * val.z().intValue();

        return x + y + z;
    }

    public Vector3i normalize() {
        double multi = 1 / lenght();
        elementData[0] = x() * multi;
        elementData[1] = y() * multi;
        elementData[2] = z() * multi;

        return this;
    }

    public Vector3i reverse() {
        elementData[0] = -x();
        elementData[1] = -y();
        elementData[2] = -z();

        return this;
    }

    public static Vector3i toVec3i(Vector3<? extends Number> val) {
        return new Vector3i(val.x().intValue(), val.y().intValue(), val.z().intValue());
    }
}
