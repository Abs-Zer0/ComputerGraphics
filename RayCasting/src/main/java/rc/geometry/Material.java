/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.geometry;

import rc.Color;

/**
 *
 * @author ���0������ �0��
 *
 * ����� ���������
 */
public class Material {

    private final Color color;
    private final double reflect;
    private final double refract;
    private final double phong;

    /**
     * ������ ��������� ������ � ��������� ������ - color, �������������� �����
     * - phong, ��������� - reflection, ����������� - refraction
     */
    public Material(Color color, double phong, double reflection, double refraction) {
        this.color = color == null ? Color.white() : color;
        this.phong = phong < 0.0 ? 0.0 : phong;
        this.reflect = reflection < 0.0 ? 0.0 : (reflection > 1.0 ? 1.0 : reflection);
        //this.reflect = reflection < 0.0 ? 0.0 : reflection;
        this.refract = refraction < 0.0 ? 0.0 : refraction;
    }

    /**
     * ������ ��������� ������ � ��������� ������ - color, �������������� �����
     * - phong, ��������� - reflection
     */
    public Material(Color color, double phong, double reflection) {
        this(color, phong, reflection, 0.0);
    }

    /**
     * ������ ��������� ������ � ��������� ������ - color, ������������� �����
     * - phong
     */
    public Material(Color color, double phong) {
        this(color, phong, 0.0);
    }

    /**
     * ������ ��������� ������ � �������� ������ - color
     */
    public Material(Color color) {
        this(color, 0.0);
    }

    /**
     * ������ ������� ��������� ������
     */
    public Material() {
        this(null);
    }

    /**
     * ����
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * ����������� �����
     */
    public double getPhong() {
        return this.phong;
    }

    /**
     * ����������� ���������
     */
    public double getReflection() {
        return this.reflect;
    }

    /**
     * ����������� �����������
     */
    public double getRefraction() {
        return this.refract;
    }

    @Override
    public Material clone() {
        return new Material(this.color, this.phong, this.reflect, this.refract);
    }

    /**
     * ����������� ��������
     */
    public static Material defaultMaterial() {
        return new Material();
    }
}
