/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.geometry;

import rc.math.Ray;
import rc.math.Vector2;
import rc.scene.Transform;

/**
 *
 * @author ���0������ �0��
 * 
 * ����� ������ �������������
 */
public class Rectangle extends Shape {

    private Triangle first, second;
    private double width, height;

    /**
    * ������ ��������� ������ � ��������� ������� - width, ������� - height,
    * ���������� - material
    */
    public Rectangle(double width, double height, Material material) {
        super(material);
        this.width = width < 0.0 ? 0.0 : width;
        this.height = height < 0.0 ? 0.0 : height;
        generateTriangles();
    }

    /**
    * ������ ��������� ������ � ��������� ������� - width, ������� - height,
    */
    public Rectangle(double width, double height) {
        this(width, height, null);
    }

    /**
    * ������ ��������� ������ � �������� ���������� - material
    */
    public Rectangle(Material material) {
        this(0.0, 0.0, material);
    }

    /**
    * ������ ��������������
    */
    public double getWidth() {
        return this.width;
    }

    /**
    * ���������� ������ ��������������
    * ���������� ������� ����������� ������, 0.0 ���� ��� �������� ������������� ��������
    */
    public double setWidth(double newWidth) {
        this.width = newWidth < 0.0 ? 0.0 : newWidth;
        generateTriangles();

        return this.width;
    }

    /**
    * ������ ��������������
    */
    public double getHeight() {
        return this.height;
    }

    /**
    * ���������� ������ ��������������
    * ���������� ������� ����������� ������, 0.0 ���� ��� �������� ������������� ��������
    */
    public double setHeight(double newHeight) {
        this.height = newHeight < 0.0 ? 0.0 : newHeight;
        generateTriangles();

        return this.height;
    }

    private void generateTriangles() {
        this.first = generateFirstTriangle();
        this.second = generateSecondTriangle();
    }

    private Triangle generateFirstTriangle() {
        final Vector2 A = new Vector2(-this.width * 0.5, this.height * 0.5);
        final Vector2 B = new Vector2(this.width * 0.5, this.height * 0.5);
        final Vector2 C = new Vector2(this.width * 0.5, -this.height * 0.5);

        return new Triangle(A, B, C);
    }

    private Triangle generateSecondTriangle() {
        final Vector2 A = new Vector2(-this.width * 0.5, this.height * 0.5);
        final Vector2 B = new Vector2(this.width * 0.5, -this.height * 0.5);
        final Vector2 C = new Vector2(-this.width * 0.5, -this.height * 0.5);

        return new Triangle(A, B, C);
    }

    /**
    * ������� ���������� ����������� ���� ray � ��������������� � �������� ������� transform
    * ���������� ����������, ����������� ��� ���������� ���������
    */
    @Override
    public IntersectResult isIntersected(Ray ray, Transform transform) {
        final IntersectResult byFirst = this.first.isIntersected(ray, transform);
        final IntersectResult bySecond = this.second.isIntersected(ray, transform);

        if (!byFirst.isMiss()) {
            return new IntersectResult(this, byFirst.getNormal(), byFirst.getType(), byFirst.getDistance());
        } else if (!bySecond.isMiss()) {
            return new IntersectResult(this, bySecond.getNormal(), bySecond.getType(), bySecond.getDistance());
        } else {
            return IntersectResult.miss();
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

        Rectangle rect = (Rectangle) obj;
        return this.width == rect.width && this.height == rect.height;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.width) ^ (Double.doubleToLongBits(this.width) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.height) ^ (Double.doubleToLongBits(this.height) >>> 32));
        return hash;
    }
}
