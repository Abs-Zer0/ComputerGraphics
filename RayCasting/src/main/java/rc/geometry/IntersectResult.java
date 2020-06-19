/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.geometry;

import rc.math.*;

/**
 *
 * @author Абс0лютный Н0ль
 * 
 * Класс, связывающий фигуру с вычисленными данными
 */
public class IntersectResult {

    private final Shape shape;
    private final Ray normal;
    private final RayType type;
    private final double distance;
    
    public IntersectResult(Shape shape, Ray norm, RayType type, double dist) {
        this.shape = shape;
        this.normal = norm;
        this.type = type;
        this.distance = dist;
    }

    /**
    * Фигура
    */
    public Shape getShape() {
        return this.shape;
    }

    /**
    * Нормаль из точки пересечения луча и фигуры
    */
    public Ray getNormal() {
        return this.normal;
    }

    /**
    * Тип потегциально пересекающего луча
    */
    public RayType getType() {
        return this.type;
    }

    /**
    * Расстояние от начала луча до точки пересечения
    */
    public double getDistance() {
        return this.distance;
    }

    /**
    * Показывает промах луча
    * Возвращает true - если луч не имеет точек пересечения с фигурой,
    * false - если луч имеет одну или несколько точек пересечения с фигурой
    */
    public boolean isMiss() {
        return this.type == RayType.MISS;
    }

    /**
    * Экземпляр промахнувшегося луча
    */
    public static IntersectResult miss() {
        return new IntersectResult(null, null, RayType.MISS, 0);
    }
}
