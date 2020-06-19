/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.math;

import java.util.Objects;

/**
 *
 * @author ���0������ �0��
 * 
 * ����� ����
 */
public class Ray {

    private Vector3 origin, direction;

    /**
    * ������ ��������� ������ � ��������� ������� ���� - origin, ������������ ���� - direction
    */
    public Ray(Vector3 origin, Vector3 direction) {
        this.origin = origin == null ? Vector3.zero() : origin;
        this.direction = direction == null ? new Vector3()
                : (direction.equals(Vector3.zero()) ? Vector3.i() : direction.normalized());
    }

    /**
    * ������ ��������� ������ � �������� ������������ ���� - direction
    */
    public Ray(Vector3 direction) {
        this(Vector3.zero(), direction);
    }

    /**
    * ������ ������� ��������� ������
    */
    public Ray() {
        this(Vector3.i());
    }

    /**
    * ������ ����
    */
    public Vector3 getOrigin() {
        return this.origin;
    }

    public Vector3 setOrigin(Vector3 origin) {
        this.origin = origin == null ? Vector3.zero() : origin;
        return this.origin;
    }

    /**
    * ����������� ����
    */
    public Vector3 getDirection() {
        return this.direction;
    }

    public Vector3 setDirection(Vector3 direction) {
        this.direction = direction == null ? new Vector3()
                : (direction.equals(Vector3.zero()) ? Vector3.i() : direction.normalized());
        return this.direction;
    }

    /**
    * ��������� ��� ������������ ������� ����
    */
    public Ray reflected(Ray norm) {
        return new Ray(norm.origin, this.direction.reflected(norm.direction));
    }

    /**
    * ������������ ��� ������������ ������� ���� �� ����������� - k
    */
    public Ray refracted(Ray norm, double k) {
        final Vector3 dir = this.direction.refracted(norm.direction, k);
        if (dir != null) {
            return new Ray(norm.origin, dir);
        } else {
            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Ray r = (Ray) obj;
        return this.origin.equals(r.origin) && this.direction.equals(r.direction);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.origin);
        hash = 53 * hash + Objects.hashCode(this.direction);
        return hash;
    }

    public static Ray makeFrom2Points(Vector3 start, Vector3 end) {
        Vector3 dir = end.subtract(start);
        return new Ray(start, dir);
    }

    public static Ray zero() {
        return new Ray(Vector3.zero());
    }
}
