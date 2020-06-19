/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.scene;

import java.util.*;
import rc.geometry.*;
import rc.math.*;

/**
 *
 * @author ���0������ �0��
 *
 * ����� �������, ������� ���������� ����������
 */
public class RenderObject extends Transform {

    public Shape shape;

    /**
     * ������ ��������� ������ � �������� ������� - shape, �������� - pos,
     * ��������� - rot, ������� �������� �������� - children
     */
    public RenderObject(Shape shape, Vector3 pos, Vector3 rot, Collection<? extends Transform> children) {
        super(pos, rot, children);
        this.shape = shape;
    }

    /**
     * ������ ��������� ������ � �������� ������� - shape, �������� - pos,
     * ��������� - rot
     */
    public RenderObject(Shape shape, Vector3 pos, Vector3 rot) {
        this(shape, pos, rot, null);
    }

    /**
     * ������ ��������� ������ � �������� ������� - shape, �������� - pos
     */
    public RenderObject(Shape shape, Vector3 pos) {
        this(shape, pos, null);
    }

    /**
     * ������ ��������� ������ � �������� ������� - shape
     */
    public RenderObject(Shape shape) {
        this(shape, null);
    }

    /**
     * ������ ��������� ������ � �������� �������� - pos, ��������� - rot,
     * ������� �������� �������� - children
     */
    public RenderObject(Vector3 pos, Vector3 rot, Collection<? extends Transform> children) {
        super(pos, rot, children);
    }

    /**
     * ������ ��������� ������ � �������� �������� - pos, ��������� - rot
     */
    public RenderObject(Vector3 pos, Vector3 rot) {
        this(pos, rot, null);
    }

    /**
     * ������ ��������� ������ � �������� �������� - pos
     */
    public RenderObject(Vector3 pos) {
        this(pos, null);
    }

    /**
     * ������ ������� ��������� ������
     */
    public RenderObject() {
        this(Vector3.zero(), null);
    }

    /**
     * ������� ���������� ����������� ���� � �������� ���������� ����������,
     * ����������� ��� ���������� ���������
     */
    public IntersectResult isIntersected(Ray ray) {
        return this.shape != null ? this.shape.isIntersected(ray, this) : IntersectResult.miss();
    }

    /*
    private IntersectResult childrenIntersected(Ray ray) {
        Stream<IntersectResult> res = this.children.parallelStream()
                .filter((child) -> child instanceof RenderObject)
                .map((child) -> ((RenderObject) child).isIntersected(ray))
                .filter((ir) -> ir.isMiss());
        if (res.count() == 0) {
            return IntersectResult.miss();
        }

        return res.min(Comparator.comparing(IntersectResult::getDistance)).get();

    }
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        RenderObject ro = (RenderObject) obj;
        if (this.shape == null || ro.shape == null) {
            return false;
        }
        boolean sup = super.equals(ro);
        boolean shp = this.shape.equals(ro.shape);

        return sup && shp;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.shape);
        return hash;
    }

    @Override
    public RenderObject clone() {
        if (this.shape != null) {
            return new RenderObject(this.shape.clone(),
                    this.globalPosition.clone(),
                    this.globalRotation.clone(),
                    (Vector<Transform>) this.children.clone());
        } else {
            return new RenderObject(this.globalPosition.clone(),
                    this.globalRotation.clone(),
                    (Vector<Transform>) this.children.clone());
        }
    }
}
