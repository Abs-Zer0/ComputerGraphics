/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.graphics.opengl.utils;

import java.util.Objects;

/**
 *
 * @author Абс0лютный Н0ль
 * @param <T>
 */
public class Vector3<T extends Number> {

    private T x, y, z;

    public Vector3(T x, T y, T z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(T value) {
        this(value, value, value);
    }

    public T X() {
        return this.x;
    }

    public T Y() {
        return this.y;
    }

    public T Z() {
        return this.z;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Vector3 vec = (Vector3) obj;
        return vec.x.doubleValue() == this.x.doubleValue()
                && vec.y.doubleValue() == this.y.doubleValue()
                && vec.z.doubleValue() == this.z.doubleValue();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.x);
        hash = 53 * hash + Objects.hashCode(this.y);
        hash = 53 * hash + Objects.hashCode(this.z);

        return hash;
    }

    @Override
    public String toString() {
        String res = "(";
        res += this.x.toString();
        res += ", ";
        res += this.y.toString();
        res += ", ";
        res += this.z.toString();
        res += ")";

        return res;
    }

    public boolean Equals(Vector3 value) {
        return this.equals(value);
    }

    public static Vector3 add(Vector3 first, Vector3 second) {
        first.x = first.x.doubleValue() + second.x.doubleValue();
        first.y = first.y.doubleValue() + second.y.doubleValue();
        first.z = first.z.doubleValue() + second.z.doubleValue();

        return first;
    }

    public Vector3 Add(Vector3 value) {
        return Vector3.add(this, value);
    }

    public static Vector3 multiply(Number value, Vector3 vec) {
        vec.x = value.doubleValue() * vec.x.doubleValue();
        vec.y = value.doubleValue() * vec.y.doubleValue();
        vec.z = value.doubleValue() * vec.z.doubleValue();

        return vec;
    }

    public Vector3 Multiply(Number value) {
        return Vector3.multiply(value, this);
    }

    public static Double multiply(Vector3 first, Vector3 second) {
        return first.x.doubleValue() * second.x.doubleValue()
                + first.y.doubleValue() * second.y.doubleValue()
                + first.z.doubleValue() * second.z.doubleValue();
    }

    public Double Multiply(Vector3 value) {
        return Vector3.multiply(this, value);
    }

    public static Double lenght(Vector3 value) {
        return Math.sqrt(value.x.doubleValue() * value.x.doubleValue()
                + value.y.doubleValue() * value.y.doubleValue()
                + value.z.doubleValue() * value.z.doubleValue());
    }

    public Double Lenght() {
        return Vector3.lenght(this);
    }

    public static Vector3 normallize(Vector3 value) {
        Double multi = 1.0 / Vector3.lenght(value);
        return Vector3.multiply(multi, value);
    }

    public Vector3 Normallize() {
        return Vector3.normallize(this);
    }

    public static Vector3 negate(Vector3 value) {
        value.x = -value.x.doubleValue();
        value.y = -value.y.doubleValue();
        value.z = -value.z.doubleValue();

        return value;
    }

    public Vector3 Negate() {
        return Vector3.negate(this);
    }

    public static Vector3 rotate(Vector3 vec, double degree, Vector3 anchor) {
        Vector3 copy = new Vector3(vec.x, vec.y, vec.z);
        double sin = MathExtension.sin(degree);
        double cos = MathExtension.cos(degree);

        vec.x = copy.x.doubleValue() * (cos + (1 - cos) * copy.x.doubleValue() * copy.x.doubleValue());

        return vec;
    }

    public static Vector3 rotate(Vector3 vec, Vector3 EulerAngles, Vector3 anchor) {
        return vec;
    }
}
