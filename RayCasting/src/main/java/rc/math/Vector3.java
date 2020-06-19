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
 * Класс вектора в трёхмерном пространстве
 */
public class Vector3 {

    public double x = 0, y = 0, z = 0;

    public Vector3() {
        this(0);
    }

    public Vector3(double value) {
        this.x = value;
        this.y = value;
        this.z = value;
    }

    public Vector3(double x, double y) {
        this(x, y, 0);
    }

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(Vector2 vec) {
        this(vec.x, vec.y);
    }

    public Vector3(Vector3 vec) {
        this(vec.x, vec.y, vec.z);
    }

    /**
     * Противоположный вектор
     */
    public Vector3 negate() {
        return new Vector3(-this.x, -this.y, -this.z);
    }

    /**
     * Сложение векторов
     */
    public Vector3 add(Vector3 other) {
        return new Vector3(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    /**
     * Вычитание векторов
     */
    public Vector3 subtract(Vector3 other) {
        return new Vector3(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    /**
     * Умножение вектора на число
     */
    public Vector3 product(double value) {
        return new Vector3(this.x * value, this.y * value, this.z * value);
    }

    /**
     * Скалярное произведение векторов
     */
    public double product(Vector3 other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    /**
     * Векторное произведение векторов
     */
    public Vector3 X(Vector3 other) {
        double nx = this.y * other.z - this.z * other.y;
        double ny = this.z * other.x - this.x * other.z;
        double nz = this.x * other.y - this.y * other.x;

        return new Vector3(nx, ny, nz);
    }

    /**
     * Умножение вектора на матрицу
     */
    public Vector3 multi(Matrix3x3 mat) {
        double nx = mat.x11 * this.x + mat.x21 * this.y + mat.x31 * this.z;
        double ny = mat.x12 * this.x + mat.x22 * this.y + mat.x32 * this.z;
        double nz = mat.x13 * this.x + mat.x23 * this.y + mat.x33 * this.z;

        return new Vector3(nx, ny, nz);
    }

    /**
     * Длина вектора в квадрате
     */
    public double sqrLength() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
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
        this.z = this.z * scale;
    }

    /**
     * Нормализованный вектор
     */
    public Vector3 normalized() {
        Vector3 res = new Vector3(this);
        res.normalize();
        return res;
    }

    /**
     * Расстояние между точками в квадрате
     */
    public double sqrDistance(Vector3 other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        double dz = this.z - other.z;

        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * Расстояние между точками
     */
    public double distance(Vector3 other) {
        return Math.sqrt(sqrDistance(other));
    }

    /**
     * Отражение вектора относительно другого this - 2.0 * (this * other) *
     * other
     */
    public Vector3 reflected(Vector3 other) {
        return this.subtract(other.product(this.product(other)).product(2.0));
    }

    /**
     * Преломление вектора относительно другого на коэффициент - k (1/k) * this
     * - (cos(b) + (1/k) * (this * other)) * other cos(b) = sqrt(1 - (1/k)2 * (1
     * - (this * other)2))
     */
    public Vector3 refracted(Vector3 other, double k) {
        double nk = 1.0 / k;
        double dot = this.product(other);

        double sinb2 = nk * nk * (1.0 - dot * dot);
        if (sinb2 > 1.0) {
            return null;
        }
        double cosb = Math.sqrt(1.0 - sinb2);

        Vector3 b = other.product(cosb + nk * dot);
        Vector3 a = this.product(nk);

        return a.subtract(b);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Vector3 vec = (Vector3) obj;
        return this.x == vec.x && this.y == vec.y && this.z == vec.z;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.z) ^ (Double.doubleToLongBits(this.z) >>> 32));
        return hash;
    }

    @Override
    public Vector3 clone() {
        return new Vector3(this.x, this.y, this.z);
    }

    @Override
    public String toString() {
        return String.format("(%f, %f, %f)", this.x, this.y, this.z);
    }

    public static Vector3 zero() {
        return new Vector3();
    }

    public static Vector3 one() {
        return new Vector3(1);
    }

    public static Vector3 i() {
        return new Vector3(1, 0, 0);
    }

    public static Vector3 j() {
        return new Vector3(0, 1, 0);
    }

    public static Vector3 k() {
        return new Vector3(0, 0, 1);
    }
}
