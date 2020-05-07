/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.graphics.rt.utils;

import java.util.Vector;

/**
 *
 * @author Абс0лютный Н0ль
 * @param <E>
 */
public class Vector3<E extends Number> extends Vector {

    public Vector3(E val) {
        this(val, val, val);
    }

    public Vector3(E x, E y, E z) {
        super(3);

        elementData[0] = x;
        elementData[1] = y;
        elementData[2] = z;
    }

    public synchronized E x() {
        return (E) elementData[0];
    }

    public synchronized E y() {
        return (E) elementData[1];
    }

    public synchronized E z() {
        return (E) elementData[2];
    }

    public synchronized double lenght() {
        return Math.sqrt(x().doubleValue() * x().doubleValue()
                + y().doubleValue() * y().doubleValue()
                + z().doubleValue() * z().doubleValue());
    }
}
