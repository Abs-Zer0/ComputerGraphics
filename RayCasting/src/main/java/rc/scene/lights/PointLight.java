/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.scene.lights;

import java.util.Collection;
import rc.Color;
import rc.math.Ray;
import rc.math.Vector3;
import rc.scene.Transform;

/**
 *
 * @author Абс0лютный Н0ль
 * 
 * Класс точечного источника света
 */
public class PointLight extends Light {

    public PointLight(Color color, double intensity, Vector3 pos, Collection<? extends Transform> children) {
        super(color, intensity, pos, null, children);
    }

    public PointLight(Color color, double intensity, Vector3 pos) {
        this(color, intensity, pos, null);
    }

    public PointLight(Color color, double intensity) {
        this(color, intensity, null);
    }

    public PointLight() {
        this(null, 0.0);
    }

    @Override
    public ShadowRay makeShadowRay(Vector3 origin) {
        return new ShadowRay(this, new Ray(origin, this.globalPosition.subtract(origin)));
    }
}
