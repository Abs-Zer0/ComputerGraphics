/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.scene.lights;

import java.util.Collection;
import java.util.Objects;
import rc.Color;
import rc.math.*;
import rc.scene.Transform;

/**
 *
 * @author Абс0лютный Н0ль
 * 
 * Класс источника света
 */
public abstract class Light extends Transform {

    protected Color I = Color.black();
    protected double distance = 0.0;

    public Light(Color color, double intensity, Vector3 pos, Vector3 rot, Collection<? extends Transform> children) {
        super(pos, rot, children);
        setColor(color);
        setIntensity(intensity);
    }

    public Light(Color color, double intensity, Vector3 pos, Vector3 rot) {
        this(color, intensity, pos, rot, null);
    }

    public Light(Color color, double intensity, Vector3 pos) {
        this(color, intensity, pos, null);
    }

    public Light(Color color, double intensity) {
        this(color, intensity, null);
    }

    public Light() {
        this(null, 0.0);
    }

    public Color getColor() {
        return this.I;
    }

    public Color setColor(Color newColor) {
        this.I = newColor == null ? Color.black() : newColor;
        this.I.setAlpha(1);

        return this.I;
    }

    public double getIntensity() {
        return this.distance;
    }

    public double setIntensity(double intensity) {
        this.distance = intensity < 0.0 ? 0.0 : intensity;

        return this.distance;
    }

    /**
    * Создание теневого луча относительно точки
    */
    public abstract ShadowRay makeShadowRay(Vector3 origin);

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Light lght = (Light) obj;
        return this.I.equals(lght.I) && this.distance == lght.distance;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.I);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.distance) ^ (Double.doubleToLongBits(this.distance) >>> 32));
        return hash;
    }
}
