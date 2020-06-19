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
 * Класс матрицы 3Х3
 */
public class Matrix3x3 {

    double x11 = 0, x12 = 0, x13 = 0;
    double x21 = 0, x22 = 0, x23 = 0;
    double x31 = 0, x32 = 0, x33 = 0;

    /**
    * Создаёт экземпляр класса с заданными значениями
     */
    public Matrix3x3(double X11, double X12, double X13,
            double X21, double X22, double X23,
            double X31, double X32, double X33) {
        this.x11 = X11;
        this.x12 = X12;
        this.x13 = X13;
        this.x21 = X21;
        this.x22 = X22;
        this.x23 = X23;
        this.x31 = X31;
        this.x32 = X32;
        this.x33 = X33;
    }

    public Matrix3x3() {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    /**
    * Умножение матрицы на вектор
     */
    public Vector3 multi(Vector3 vec) {
        double x = vec.x * this.x11 + vec.y * this.x12 + vec.z * this.x13;
        double y = vec.x * this.x21 + vec.y * this.x22 + vec.z * this.x23;
        double z = vec.x * this.x31 + vec.y * this.x32 + vec.z * this.x33;

        return new Vector3(x, y, z);
    }

    /**
    * Умножение матрицы на матрицу
     */
    public Matrix3x3 multi(Matrix3x3 other) {
        double nx11 = this.x11 * other.x11 + this.x12 * other.x21 + this.x13 * other.x31;
        double nx12 = this.x11 * other.x12 + this.x12 * other.x22 + this.x13 * other.x32;
        double nx13 = this.x11 * other.x13 + this.x12 * other.x23 + this.x13 * other.x33;
        double nx21 = this.x21 * other.x11 + this.x22 * other.x21 + this.x23 * other.x31;
        double nx22 = this.x21 * other.x12 + this.x22 * other.x22 + this.x23 * other.x32;
        double nx23 = this.x21 * other.x13 + this.x22 * other.x23 + this.x23 * other.x33;
        double nx31 = this.x31 * other.x11 + this.x32 * other.x21 + this.x33 * other.x31;
        double nx32 = this.x31 * other.x12 + this.x32 * other.x22 + this.x33 * other.x32;
        double nx33 = this.x31 * other.x13 + this.x32 * other.x23 + this.x33 * other.x33;

        return new Matrix3x3(nx11, nx12, nx13,
                nx21, nx22, nx23,
                nx31, nx32, nx33);
    }

    /**
    * Матрица поворота вокруг оси X
     */
    public static Matrix3x3 rotateX(double deg) {
        double sin = Math.sin(Angle.getRadians(deg));
        double cos = Math.cos(Angle.getRadians(deg));

        return new Matrix3x3(1, 0, 0, 0, cos, -sin, 0, sin, cos);
    }

    /**
    * Матрица поворота вокруг оси Y
     */
    public static Matrix3x3 rotateY(double deg) {
        double sin = Math.sin(Angle.getRadians(deg));
        double cos = Math.cos(Angle.getRadians(deg));

        return new Matrix3x3(cos, 0, sin, 0, 1, 0, -sin, 0, cos);
    }

    /**
    * Матрица поворота вокруг оси Z
     */
    public static Matrix3x3 rotateZ(double deg) {
        double sin = Math.sin(Angle.getRadians(deg));
        double cos = Math.cos(Angle.getRadians(deg));

        return new Matrix3x3(cos, -sin, 0, sin, cos, 0, 0, 0, 1);
    }

    /**
    * Матрица поворота на Эйлеровские углы
     */
    public static Matrix3x3 rotateXYZ(double x, double y, double z) {
        return rotateX(x).multi(rotateY(y)).multi(rotateZ(z));
    }

    /**
    * Матрица поворота на Эйлеровские углы
     */
    public static Matrix3x3 rotateXYZ(Vector3 euler) {
        return rotateXYZ(euler.x, euler.y, euler.z);
    }

    /**
    * Матрица поворота вокруг заданного вектора
     */
    public static Matrix3x3 rotateAround(Vector3 vec, double deg) {
        double sin = Math.sin(Angle.getRadians(deg));
        double cos = Math.cos(Angle.getRadians(deg));
        double ncos = 1 - cos;

        double x11 = ncos * vec.x * vec.x + cos;
        double x12 = ncos * vec.x * vec.y + sin * vec.z;
        double x13 = ncos * vec.x * vec.z - sin * vec.y;
        double x21 = ncos * vec.x * vec.y - sin * vec.z;
        double x22 = ncos * vec.y * vec.y + cos;
        double x23 = ncos * vec.y * vec.z + sin * vec.x;
        double x31 = ncos * vec.x * vec.z + sin * vec.y;
        double x32 = ncos * vec.y * vec.z - sin * vec.x;
        double x33 = ncos * vec.z * vec.z + cos;

        return new Matrix3x3(x11, x12, x13,
                x21, x22, x23,
                x31, x32, x33);
    }

    /**
    * Единичная матрица
     */
    public static Matrix3x3 eye() {
        return new Matrix3x3(1, 0, 0, 0, 1, 0, 0, 0, 1);
    }

    /**
    * Нулевая матрица
     */
    public static Matrix3x3 zero() {
        return new Matrix3x3(0, 0, 0, 0, 0, 0, 0, 0, 0);
    }
}
