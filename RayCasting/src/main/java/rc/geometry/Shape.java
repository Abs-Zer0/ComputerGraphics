/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.geometry;

import rc.scene.Transform;
import rc.math.Ray;

/**
 *
 * @author Абс0лютный Н0ль
 * 
 * Класс фигуры
 */
public abstract class Shape {

    private Material material;

    /**
    * Создаёт экземпляр класса с заданным материалом - material
    */
    public Shape(Material material) {
        setMaterial(material);
    }

    /**
    * Создаёт базовый экземпляр класса
    */
    public Shape() {
        this(null);
    }

    /**
    * Материал фигуры
    */
    public Material getMaterial() {
        return this.material;
    }

    /**
    * Назначение материала фигуре
    * Возвращает реально назначенный материал, стандартный если был передан null
    */
    public Material setMaterial(Material newMaterial) {
        this.material = newMaterial == null ? Material.defaultMaterial() : newMaterial;

        return this.material;
    }

    /**
    * Функция вычисления пересечения луча ray с фигурой в заданной позиции transform
    * Возвращает результаты, необходимые для дальнейших рассчётов
    */
    public abstract IntersectResult isIntersected(Ray ray, Transform transform);

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();
}
