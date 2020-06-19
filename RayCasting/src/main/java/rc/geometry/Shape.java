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
 * @author ���0������ �0��
 * 
 * ����� ������
 */
public abstract class Shape {

    private Material material;

    /**
    * ������ ��������� ������ � �������� ���������� - material
    */
    public Shape(Material material) {
        setMaterial(material);
    }

    /**
    * ������ ������� ��������� ������
    */
    public Shape() {
        this(null);
    }

    /**
    * �������� ������
    */
    public Material getMaterial() {
        return this.material;
    }

    /**
    * ���������� ��������� ������
    * ���������� ������� ����������� ��������, ����������� ���� ��� ������� null
    */
    public Material setMaterial(Material newMaterial) {
        this.material = newMaterial == null ? Material.defaultMaterial() : newMaterial;

        return this.material;
    }

    /**
    * ������� ���������� ����������� ���� ray � ������� � �������� ������� transform
    * ���������� ����������, ����������� ��� ���������� ���������
    */
    public abstract IntersectResult isIntersected(Ray ray, Transform transform);

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();
}
