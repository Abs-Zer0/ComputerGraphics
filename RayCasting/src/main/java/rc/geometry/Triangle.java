/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.geometry;

import java.util.Objects;
import rc.math.Constants;
import rc.math.Ray;
import rc.math.RayType;
import rc.math.Vector2;
import rc.math.Vector3;
import rc.scene.Transform;

/**
 *
 * @author Абс0лютный Н0ль
 * 
 * Класс фигуры треугольник
 */
public class Triangle extends Shape {

    private Vector2 A = Vector2.zero(), B = Vector2.i(), C = Vector2.j();

    /**
    * Создаёт экземпляр класса по 3 точкам в двумерном пространстве с заданным материалом
    */
    public Triangle(Vector2 a, Vector2 b, Vector2 c, Material material) {
        super(material);
        setA(a);
        setB(b);
        setC(c);
    }

    /**
    * Создаёт экземпляр класса по 3 точкам в двумерном пространстве
    */
    public Triangle(Vector2 a, Vector2 b, Vector2 c) {
        this(a, b, c, null);
    }

    public Triangle(Material material) {
        this(null, null, null, material);
    }

    public Triangle() {
        this(null);
    }

    public Vector2 getA() {
        return this.A;
    }

    public Vector2 setA(Vector2 a) {
        this.A = a == null ? Vector2.zero() : a;

        return this.A;
    }

    public Vector2 getB() {
        return this.B;
    }

    public Vector2 setB(Vector2 b) {
        this.B = b == null ? Vector2.i() : b;

        return this.B;
    }

    public Vector2 getC() {
        return this.C;
    }

    public Vector2 setC(Vector2 c) {
        this.C = c == null ? Vector2.j() : c;

        return this.C;
    }

    /**
    * Функция вычисления пересечения луча ray с треугольником в заданной позиции transform
    * Возвращает результаты, необходимые для дальнейших рассчётов
    */
    @Override
    public IntersectResult isIntersected(Ray ray, Transform transform) {
        final Ray _ray = ray;
        final Transform _transform = transform;

        final Vector3 a = new Vector3(this.A).multi(_transform.getRotationMatrix())
                .add(_transform.getGlobalPostion());
        final Vector3 b = new Vector3(this.B).multi(_transform.getRotationMatrix())
                .add(_transform.getGlobalPostion());
        final Vector3 c = new Vector3(this.C).multi(_transform.getRotationMatrix())
                .add(_transform.getGlobalPostion());

        final Vector3 b_sub_a = b.subtract(a);
        final Vector3 c_sub_a = c.subtract(a);
        final Vector3 origin_sub_a = _ray.getOrigin().subtract(a);

        final double denomenator = _ray.getDirection().X(c_sub_a).product(b_sub_a);

        double enumerator = _ray.getOrigin().subtract(a).X(b_sub_a).product(c_sub_a);
        final Double t = enumerator / denomenator;
        if (t.isInfinite() || t < Constants.tolerance) {
            return IntersectResult.miss();
        }

        enumerator = _ray.getDirection().X(c_sub_a).product(origin_sub_a);
        final Double u = enumerator / denomenator;
        if (u.isInfinite() || u < Constants.tolerance) {
            return IntersectResult.miss();
        }

        enumerator = origin_sub_a.X(b_sub_a).product(_ray.getDirection());
        final Double v = enumerator / denomenator;
        if (v.isInfinite() || v < Constants.tolerance || u + v > 1.0) {
            return IntersectResult.miss();
        }

        final Vector3 origin = a.product(1.0 - u - v).add(b.product(u)).add(c.product(v));
        Vector3 direction = b.subtract(a).X(c.subtract(a));
        if (direction.product(ray.getDirection()) > 0.0) {
            direction = direction.negate();
        }
        final Ray normal = new Ray(origin, direction);

        return new IntersectResult(this, normal, RayType.TANGENT, t);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Triangle tri = (Triangle) obj;
        return this.A.equals(tri.A) && this.B.equals(tri.B) && this.C.equals(tri.C);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.A);
        hash = 79 * hash + Objects.hashCode(this.B);
        hash = 79 * hash + Objects.hashCode(this.C);
        return hash;
    }
}
