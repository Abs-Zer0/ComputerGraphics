/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.geometry;

import rc.math.*;

/**
 *
 * @author ���0������ �0��
 * 
 * �����, ����������� ������ � ������������ �������
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
    * ������
    */
    public Shape getShape() {
        return this.shape;
    }

    /**
    * ������� �� ����� ����������� ���� � ������
    */
    public Ray getNormal() {
        return this.normal;
    }

    /**
    * ��� ������������ ������������� ����
    */
    public RayType getType() {
        return this.type;
    }

    /**
    * ���������� �� ������ ���� �� ����� �����������
    */
    public double getDistance() {
        return this.distance;
    }

    /**
    * ���������� ������ ����
    * ���������� true - ���� ��� �� ����� ����� ����������� � �������,
    * false - ���� ��� ����� ���� ��� ��������� ����� ����������� � �������
    */
    public boolean isMiss() {
        return this.type == RayType.MISS;
    }

    /**
    * ��������� ��������������� ����
    */
    public static IntersectResult miss() {
        return new IntersectResult(null, null, RayType.MISS, 0);
    }
}
