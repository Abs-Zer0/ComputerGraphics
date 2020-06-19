/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.scene;

import java.util.Collection;
import java.util.Objects;
import java.util.Vector;
import rc.math.Matrix3x3;
import rc.math.Vector3;

/**
 *
 * @author ���0������ �0��
 *
 * ����� ��������� � ������������
 */
public class Transform {

    protected Transform parent = null;
    protected Vector<Transform> children = new Vector<>();

    protected Vector3 localPosition, localRotation;
    protected Vector3 globalPosition, globalRotation;
    protected Matrix3x3 rotMat;

    /**
     * ������ ��������� ������ ��������� � ������������ � �������� - pos,
     * ��������� - rot, ������� �������� �������� - children
     */
    public Transform(Vector3 pos, Vector3 rot, Collection<? extends Transform> children) {
        this.localPosition = pos == null ? Vector3.zero() : pos;
        this.localRotation = rot == null ? Vector3.zero() : rot;
        this.globalPosition = this.localPosition;
        this.globalRotation = this.localRotation;
        this.rotMat = Matrix3x3.rotateXYZ(this.globalRotation);

        if (children != null) {
            setChildren(children);
        }
    }

    /**
     * ������ ��������� ������ ��������� � ������������ � �������� - pos,
     * ��������� - rot
     */
    public Transform(Vector3 pos, Vector3 rot) {
        this(pos, rot, null);
    }

    /**
     * ������ ��������� ������ ��������� � ������������ � �������� - pos
     */
    public Transform(Vector3 pos) {
        this(pos, Vector3.zero());
    }

    /**
     * ������ ��������� ������ ��������� � ������������ � ����������� ��� �
     * ������� - parent
     */
    public Transform(Transform parent) {
        this(Vector3.zero());
        setParent(parent);
    }

    /**
     * ������ ������� ��������� ������
     */
    public Transform() {
        this(Vector3.zero());
    }

    /**
     * ��������� ��������� ������� ������������ ������������� ������� ���
     * ���������� ��������� ������������ �����, ���� ������������� ������� ���
     */
    public Vector3 getLocalPosition() {
        return this.localPosition;
    }

    /**
     * ���������� ��������� ������� ������������ �����
     */
    public Vector3 getGlobalPosition() {
        return this.globalPosition;
    }

    /**
     * ���������� ���������� ��������� �������, ������������ ���� ������� null
     * ���������� �������� ��������� ��������� �������
     */
    public Vector3 setPosition(Vector3 pos) {
        final Vector3 old = this.localPosition;
        this.localPosition = pos == null ? Vector3.zero() : pos;
        final Vector3 dir = this.localPosition.subtract(old);

        updateGlobalPosition();

        return this.localPosition;
    }

    /**
     * ����������� ������� � ������������ �� ���������� ���� ������� null
     * ���������� �������� ����� �������
     */
    public Vector3 translate(Vector3 dir) {
        final Vector3 direction = dir == null ? Vector3.zero() : dir;

        this.localPosition = this.localPosition.add(direction);
        updateGlobalPosition();

        return direction;
    }

    private void updateGlobalPosition() {
        if (this.parent != null) {
            this.globalPosition = this.parent.globalPosition.add(this.localPosition);
        } else {
            this.globalPosition = this.localPosition;
        }

        if (!this.children.isEmpty()) {
            this.children.forEach(child -> child.updateGlobalPosition());
        }
    }

    /**
     * ��������� ������� ������� ������������ ������������� ������� ���
     * ���������� ������� ������������ �����, ���� ������������� ������� ���
     */
    public Vector3 getLocalRotation() {
        return this.localRotation;
    }

    /**
     * ���������� ������� ������������ �����
     */
    public Vector3 getGlobalRotation() {
        return this.globalRotation;
    }

    /**
     * ���������� ���������� �������� �������, ������������ ���� ������� null
     * ���������� �������� ��������� ������� �������
     */
    public Vector3 setRotation(Vector3 rot) {
        Vector3 old = this.localRotation;
        this.localRotation = rot == null ? Vector3.zero() : rot;
        updateGlobalRotation();
        this.rotMat = Matrix3x3.rotateXYZ(this.globalRotation);

        return this.localRotation;
    }

    /**
     * ������� ������� � ������������ �� ������������ ���� ������� null
     * ���������� �������� ������� �������
     */
    public Vector3 rotate(Vector3 euler) {
        final Vector3 eul = euler == null ? Vector3.zero() : euler;

        this.localRotation = this.localRotation.add(eul);
        updateGlobalRotation();
        this.rotMat = Matrix3x3.rotateXYZ(eul);

        return eul;
    }
    
    private void updateGlobalRotation() {
        if (this.parent != null) {
            this.globalRotation = this.parent.globalRotation.add(this.localRotation);
        } else {
            this.globalRotation = this.globalRotation;
        }

        if (!this.children.isEmpty()) {
            this.children.forEach(child -> child.updateGlobalRotation());
        }
    }

    /**
     * ������� �������� ��� �������
     */
    public Matrix3x3 getRotationMatrix() {
        return this.rotMat;
    }

    /**
     * ������ �� ������������ ������
     */
    public Transform getParent() {
        return this.parent;
    }

    /**
     * ���������� ������������� ������� ����� �������
     */
    public void setParent(Transform newParent) {
        if (this.parent != null) {
            this.parent.children.removeElement(this);

            this.parent = newParent;
            if (this.parent != null) {
                this.localPosition = this.globalPosition.subtract(this.parent.globalPosition);
                this.localRotation = this.globalRotation.subtract(this.parent.globalRotation);
            } else {
                this.localPosition = this.globalPosition;
                this.localRotation = this.globalRotation;
            }
        } else {
            this.parent = newParent;
            if (this.parent != null) {
                updateGlobalPosition();
                updateGlobalRotation();
            } else {
                this.localPosition = this.globalPosition;
                this.localRotation = this.globalRotation;
            }
        }
    }

    /**
     * ������ �������� ��������
     */
    public Vector<Transform> getChildren() {
        return this.children;
    }

    /**
     * ������ ���� �������� ��������
     */
    public Vector<Transform> getFlattenChildren() {
        Vector<Transform> res = new Vector<>();
        this.children.forEach(child -> res.addAll(child.getFlattenChildren()));
        res.addElement(this);

        return res;
    }

    /**
     * ���������� �������� ������� ����� �������
     */
    public void setChildren(Collection<? extends Transform> children) {
        this.children.clear();
        if (children != null) {
            children.forEach(child -> addChild(child));
        }
    }

    /**
     * ���������� ��������� ������� � ����� ������� ���������� ������ ��
     * ����������� ������
     */
    public Transform addChild(Transform child) {
        if (child != null) {
            this.children.addElement(child);
            child.setParent(this);
        }

        return child;
    }

    /**
     * ���������� ���������� �������� �������� � ����� �������
     */
    public void addChildren(Collection<? extends Transform> children) {
        if (children != null) {
            children.forEach(child -> addChild(child));
        }
    }

    /**
     * ���������� ���������� �������� �������� � ����� �������
     */
    public void addChildren(Transform... children) {
        if (children != null) {
            for (Transform child : children) {
                addChild(child);
            }
        }
    }

    /**
     * �������� ��������� ������� ���������� ������ �� �������� ������, null -
     * ���� ������ ��� ��� ��� ������� null
     */
    public Transform removeChild(Transform child) {
        if (child == null) {
            return null;
        }

        if (this.equals(child)) {
            if (this.parent != null) {
                this.setParent(null);
            }

            return this;
        }

        if (this.children.isEmpty()) {
            return null;
        }

        RenderObject[] rights = this.children.parallelStream().map((c) -> c.removeChild(child))
                .filter((c) -> c != null)
                .toArray(RenderObject[]::new);
        if (rights.length == 0) {
            return null;
        } else {
            return rights[0];
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

        Transform trans = (Transform) obj;
        boolean pos = this.localPosition.equals(trans.localPosition);
        boolean rot = this.localRotation.equals(trans.localRotation);
        boolean par = this.parent == trans.parent;
        boolean chl = this.children.equals(trans.children);

        return pos && rot && par && chl;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.parent);
        hash = 97 * hash + Objects.hashCode(this.children);
        hash = 97 * hash + Objects.hashCode(this.localPosition);
        hash = 97 * hash + Objects.hashCode(this.localRotation);
        return hash;
    }

    @Override
    public Transform clone() {
        return new Transform(this.globalPosition.clone(),
                this.globalRotation.clone(),
                (Vector<Transform>) this.children.clone());
    }

    /*
    * ����������� ��������� � ������������
     */
    public static Transform zero() {
        return new Transform();
    }
}
