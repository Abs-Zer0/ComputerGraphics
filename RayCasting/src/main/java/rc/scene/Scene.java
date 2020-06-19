/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.scene;

import java.util.*;
import rc.Color;
import rc.geometry.*;
import rc.math.*;
import rc.scene.lights.DirectionLight;

/**
 *
 * @author ���0������ �0��
 * 
 * ����� �����, ���������� �� ������������, � ������� ��������� �������
 */
public class Scene {

    private Camera camera;
    private Vector<Transform> objs;
    private Color ambient;

    /**
    * ������ ��������� ������ � ����������� ������� - camera, ������� ���������� - ambient
    */
    public Scene(Camera camera, Color ambient) {
        this.camera = camera == null ? new Camera() : camera;
        this.objs = new Vector<>();
        this.ambient = ambient == null ? Color.darkGrey() : ambient;
        this.ambient.setAlpha(1);
    }

    /**
    * ������ ��������� ������ � ����������� ������� - camera
    */
    public Scene(Camera camera) {
        this(camera, null);
    }

    /**
    * ����������� ������
    */
    public Camera getCamera() {
        return this.camera;
    }

    /**
    * �������������� ������ ��� �����
    * ���������� ����������� ������, ����������� ���� ��� ������� null
    */
    public Camera setCamera(Camera newCamera) {
        this.camera = newCamera == null ? new Camera() : newCamera;
        return this.camera;
    }

    /**
    * ������� ���������
    */
    public Color I() {
        return this.ambient;
    }

    /**
    * ���������� �������� ���������
    */
    public Color setI(Color color) {
        this.ambient = ambient == null ? Color.darkGrey() : ambient;
        this.ambient.setAlpha(1);

        return this.ambient;
    }

    /**
    * ������ �������� ��������
    */
    public Vector<Transform> getObjects() {
        return this.objs;
    }

    /**
    * ������ ���� �������� �� �����
    */
    public Vector<Transform> getFlattenObjects() {
        Vector<Transform> res = new Vector<>();
        this.objs.forEach(obj -> res.addAll(obj.getFlattenChildren()));

        return res;
    }

    /**
    * ���������� �������� ��������
    */
    public void setObjects(Collection<? extends Transform> objects) {
        this.objs.clear();
        if (objects != null) {
            objects.forEach(obj -> addObject(obj));
        }
    }

    /**
    * ���������� ��������� �������
    * ���������� ����������� ������
    */
    public Transform addObject(Transform newObject) {
        if (newObject != null) {
            this.objs.addElement(newObject);
        }

        return newObject;
    }

    /**
    * ���������� �������� ��������
    */
    public void addObjects(Collection<? extends Transform> objects) {
        if (objects != null) {
            objects.forEach(obj -> addObject(obj));
        }
    }

    /**
    * ���������� �������� ��������
    */
    public void addObjects(Transform... objects) {
        if (objects != null) {
            for (Transform obj : objects) {
                addObject(obj);
            }
        }
    }

    /**
    * �������� ����� ��� �������
    */
    public static Scene createExampleScene() {
        Camera cam = new Camera(new Transform(new Vector3(0, 10, -20), new Vector3(-30, 0, 0)), 800, 480, 120, 100);
        Scene scn = new Scene(cam);

        DirectionLight sun = new DirectionLight(new Color(1, 1, 1), 100);
        sun.setPostion(new Vector3(50, 50, 0));
        sun.setRotation(new Vector3(0, 45, 45));

        RenderObject obj1 = new RenderObject(new Vector3(-5, 0, 2));
        Sphere sphere1 = new Sphere(5, new Material(new Color(0.0, 1, 0, 0), 3, 1, 1.0));
        obj1.shape = sphere1;

        RenderObject obj2 = new RenderObject(new Vector3(5, 0, 0));
        Sphere sphere2 = new Sphere(5, new Material(Color.green(), 2, 1));
        obj2.shape = sphere2;

        RenderObject obj3 = new RenderObject(new Vector3(0, 0, 5), new Vector3(90, 0, 0));
        Rectangle rect1 = new Rectangle(10, 10, new Material(Color.white()));
        obj3.shape = rect1;

        scn.addObjects(sun, obj1, obj2, obj3);

        return scn;
    }
}
