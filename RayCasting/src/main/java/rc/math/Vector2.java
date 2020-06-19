/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.math;

/**
 *
 * @author Абс0лютный Н0ль
 *
 * Класс вектора в двумерном пространстве
 */
public class Vector2 {

    public double x = 0, y = 0;

    public Vector2() {
        this(0);
    }

    public Vector2(double value) {
        this.x = value;
        this.y = value;
    }

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 vec) {
        this(vec.x, vec.y);
    }

    public Vector2(Vector3 vec) {
        this(vec.x, vec.y);
    }

    /**
     * Противоположный вектор
     */
    public Vector2 negate() {
        return new Vector2(-this.x, -this.y);
    }

    /**
     * Сложение векторов
     */
    public Vector2 add(Vector2 other) {
        return new Vector2(this.x + other.x, this.y + other.y);
    }

    /**
     * Вычитание векторов
     */
    public Vector2 subtract(Vector2 other) {
        return new Vector2(this.x - other.x, this.y - other.y);
    }

    /**
     * Умножение вектора на число
     */
    public Vector2 product(double value) {
        return new Vector2(this.x * value, this.y * value);
    }

    /**
     * Скалярное произведение векторов
     */
    public double product(Vector2 other) {
        return this.x * other.x + this.y * other.y;
    }

    /**
     * Длина вектора в квадрате
     */
    public double sqrLength() {
        return this.x * this.x + this.y * this.y;
    }

    /**
     * Длина вектора
     */
    public double lenght() {
        return Math.sqrt(sqrLength());
    }

    /**
     * Нормализация вектора
     */
    public void normalize() {
        double scale = 1.0 / lenght();
        this.x = this.x * scale;
        this.y = this.y * scale;
    }

    /**
     * Нормализованный вектор
     */
    public Vector2 normalized() {
        Vector2 res = new Vector2(this);
        res.normalize();
        return res;
    }

    /**
     * Расстояние между точками в квадрате
     */
    public double sqrDistance(Vector2 other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;

        return dx * dx + dy * dy;
    }

    /**
     * Расстояние между точками
     */
    public double distance(Vector2 other) {
        return Math.sqrt(sqrDistance(other));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Vector2 vec = (Vector2) obj;
        return this.x == vec.x && this.y == vec.y;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        return hash;
    }

    @Override
    public Vector2 clone() {
        return new Vector2(this.x, this.y);
    }

    public static Vector2 zero() {
        return new Vector2();
    }

    public static Vector2 one() {
        return new Vector2(1);
    }

    public static Vector2 i() {
        return new Vector2(1, 0);
    }

    public static Vector2 j() {
        return new Vector2(0, 1);
    }
}
