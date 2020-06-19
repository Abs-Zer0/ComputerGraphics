/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.scene.lights;

import java.util.Collection;
import java.util.Objects;
import rc.Color;
import rc.math.Ray;
import rc.math.Vector3;
import rc.scene.Transform;

/**
 *
 * @author Абс0лютный Н0ль
 * 
 * Класс направленного источника света
 */
public class DirectionLight extends Light {

    private static final Vector3 defDirection = Vector3.j().negate();
    private Vector3 direction = defDirection;

    public DirectionLight(Color color, double intensity, Vector3 pos, Vector3 rot, Collection<? extends Transform> children) {
        super(color, intensity, pos, rot, children);
    }

    public DirectionLight(Color color, double intensity, Vector3 pos, Vector3 rot) {
        this(color, intensity, pos, rot, null);
    }

    public DirectionLight(Color color, double intensity, Vector3 pos) {
        this(color, intensity, pos, null);
    }

    public DirectionLight(Color color, double intensity) {
        this(color, intensity, null);
    }

    public DirectionLight() {
        this(null, 0.0);
    }

    @Override
    public Vector3 setRotation(Vector3 rot) {
        Vector3 res = super.setRotation(rot);

        this.direction = defDirection.multi(this.rotMat);

        return res;
    }

    @Override
    public Vector3 rotate(Vector3 euler) {
        Vector3 res = super.rotate(euler);

        this.direction = defDirection.multi(this.rotMat);

        return res;
    }

    @Override
    public ShadowRay makeShadowRay(Vector3 origin) {
        return new ShadowRay(this, new Ray(origin, this.direction.negate()));
    }

    @Override
    public boolean equals(Object obj) {
        boolean supEquals = super.equals(obj);
        if (!supEquals) {
            return false;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        DirectionLight dirLight = (DirectionLight) obj;
        return this.direction.equals(dirLight.direction);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.direction);
        return hash;
    }
}
