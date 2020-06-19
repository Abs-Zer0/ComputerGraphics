/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.scene.lights;

import java.util.Objects;
import rc.math.Ray;
import rc.math.Vector3;

/**
 *
 * @author Абс0лютный Н0ль
 * 
 * Класс, связывающий источник света и теневой луч
 */
public class ShadowRay {

    private final Light light;
    private final Ray ray;

    public ShadowRay(Light light, Ray ray) {
        this.light = light;
        this.ray = ray;
    }

    /**
    * Источник света
    */
    public Light getLight() {
        return this.light;
    }

    /**
     * Теневой луч
     * @return 
     */
    public Ray getShadowRay() {
        return this.ray;
    }

    /**
     * Расстояние от точки пересечения фигуры с лучом до источника света
     * @return 
     */
    public double lenghtRay() {
        return this.light.getGlobalPostion().subtract(this.ray.getOrigin()).lenght();
    }

    /**
     * Косинус угла между нормалью из точки персечения фигуры с лучом и теневым лучом
     * @param normal вектор направления нормали
     * @return 
     */
    public double cos(Vector3 normal) {
        return this.ray.getDirection().product(normal);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        ShadowRay shd = (ShadowRay) obj;
        return this.light.equals(shd.light) && this.ray.equals(shd.ray);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.light);
        hash = 89 * hash + Objects.hashCode(this.ray);
        return hash;
    }
}
