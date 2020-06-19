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
import rc.scene.lights.PointLight;

/**
 *
 * @author Абс0лютный Н0ль
 *
 * Класс сцены, отвечающий за пространство, в котором находятся объекты
 */
public class Scene {

    private Camera camera;
    private Vector<Transform> objs;
    private Color ambient;

    /**
     * Создаёт экземпляр класса с назначенной камерой - camera, фоновым
     * освещением - ambient
     */
    public Scene(Camera camera, Color ambient) {
        this.camera = camera == null ? new Camera() : camera;
        this.objs = new Vector<>();
        this.ambient = ambient == null ? Color.darkGrey() : ambient;
        this.ambient.setAlpha(1);
    }

    /**
     * Создаёт экземпляр класса с назначенной камерой - camera
     */
    public Scene(Camera camera) {
        this(camera, null);
    }

    /**
     * Назначенная камера
     */
    public Camera getCamera() {
        return this.camera;
    }

    /**
     * Переназначение камеры для сцены Возвращает назначенную камеру,
     * стандартную если был передан null
     */
    public Camera setCamera(Camera newCamera) {
        this.camera = newCamera == null ? new Camera() : newCamera;
        return this.camera;
    }

    /**
     * Фоновое освещение
     */
    public Color I() {
        return this.ambient;
    }

    /**
     * Назначение фонового освещения
     */
    public Color setI(Color color) {
        this.ambient = ambient == null ? Color.darkGrey() : ambient;
        this.ambient.setAlpha(1);

        return this.ambient;
    }

    /**
     * Список дочерних объектов
     */
    public Vector<Transform> getObjects() {
        return this.objs;
    }

    /**
     * Список всех объектов на сцене
     */
    public Vector<Transform> getFlattenObjects() {
        Vector<Transform> res = new Vector<>();
        this.objs.forEach(obj -> res.addAll(obj.getFlattenChildren()));

        return res;
    }

    /**
     * Назначение дочерних объектов
     */
    public void setObjects(Collection<? extends Transform> objects) {
        this.objs.clear();
        if (objects != null) {
            objects.forEach(obj -> addObject(obj));
        }
    }

    /**
     * Добавление дочернего объекта Возвращает добавленный объект
     */
    public Transform addObject(Transform newObject) {
        if (newObject != null) {
            this.objs.addElement(newObject);
        }

        return newObject;
    }

    /**
     * Добавление дочерних объектов
     */
    public void addObjects(Collection<? extends Transform> objects) {
        if (objects != null) {
            objects.forEach(obj -> addObject(obj));
        }
    }

    /**
     * Добавление дочерних объектов
     */
    public void addObjects(Transform... objects) {
        if (objects != null) {
            for (Transform obj : objects) {
                addObject(obj);
            }
        }
    }

    /**
     * Создание сцены для примера
     */
    public static Scene createExampleScene() {
        Camera cam = new Camera(1280, 720, 120, 100);
        cam.transform.setPosition(new Vector3(0, 12, -20));
        cam.transform.setRotation(new Vector3(-17, 0, 0));

        Scene scn = new Scene(cam);

        PointLight lamp = new PointLight(Color.white(), 100, new Vector3(0, 15));
        RenderObject floor = createFloor();
        RenderObject mirrorBox = createMirrorBox();
        RenderObject table = createTable();
        RenderObject balls = createBalls();

        scn.addObjects(lamp, floor, mirrorBox, table, balls);

        return scn;
    }

    private static RenderObject createFloor() {
        final RenderObject result = new RenderObject(new Vector3(0, -2));
        double scale = 5;

        for (int i = 0; i < 22; i++) {
            for (int j = 0; j < 22; j++) {
                final RenderObject ro = new RenderObject(new Vector3((i - 11) * scale, 0, (j - 11) * scale));
                ro.setRotation(new Vector3(90, 0, 0));

                final Rectangle rect = new Rectangle(scale, scale);
                final Color color = (i % 2 == j % 2) ? Color.black() : Color.red();
                rect.setMaterial(new Material(color, 3));

                ro.shape = rect;

                result.addChild(ro);
            }
        }

        return result;
    }

    private static RenderObject createMirrorBox() {
        final RenderObject result = new RenderObject();

        final Rectangle transparent = new Rectangle(100, 100);
        transparent.setMaterial(new Material(Color.lightGrey(), 1, 0.85));
        final Rectangle grey = new Rectangle(100, 100);
        grey.setMaterial(new Material(Color.darkGrey(), 1));

        final RenderObject plane1 = new RenderObject(new Vector3(0, 0, 45));
        plane1.shape = transparent;

        final RenderObject plane2 = new RenderObject(new Vector3(0, 0, -45));
        plane2.shape = grey;

        final RenderObject plane3 = new RenderObject(new Vector3(45, 0, 0), new Vector3(0, 90));
        plane3.shape = transparent;

        final RenderObject plane4 = new RenderObject(new Vector3(-45, 0, 0), new Vector3(0, 90));
        plane4.shape = grey;

        result.addChildren(plane1, plane2, plane3, plane4);

        return result;
    }

    private static RenderObject createTable() {
        final RenderObject result = new RenderObject(Vector3.zero()/*, new Vector3(0, 90)*/);

        final Material black = new Material(new Color(0.52, 0.11, 0.1), 2);
        final Material green = new Material(new Color(0.29, 0.38, 0.25));

        final Rectangle field = new Rectangle(40, 16, green);
        final Rectangle backField = new Rectangle(40, 16, black);
        final Rectangle widthSideBorder = new Rectangle(44, 2, black);
        final Rectangle widthTopBorder = new Rectangle(44, 2, black);
        final Rectangle depthSideBorder = new Rectangle(20, 2, black);
        final Rectangle depthTopBorder = new Rectangle(2, 20, black);

        final RenderObject fieldObject = new RenderObject(new Vector3(0, 5), new Vector3(90, 0));
        fieldObject.shape = field;
        final RenderObject backFieldObject = new RenderObject(new Vector3(0, 4), new Vector3(90, 0));
        backFieldObject.shape = backField;

        final Vector<RenderObject> widthBorder = new Vector<>();
        widthBorder.addElement(new RenderObject(widthSideBorder, new Vector3(0, 5, 10)));
        widthBorder.addElement(new RenderObject(widthSideBorder, new Vector3(0, 5, 8)));
        widthBorder.addElement(new RenderObject(widthSideBorder, new Vector3(0, 5, -8)));
        widthBorder.addElement(new RenderObject(widthSideBorder, new Vector3(0, 5, -10)));

        final Vector3 widthTopBorderRot = new Vector3(90, 0);
        widthBorder.addElement(new RenderObject(widthTopBorder, new Vector3(0, 6, 9), widthTopBorderRot));
        widthBorder.addElement(new RenderObject(widthTopBorder, new Vector3(0, 6, -9), widthTopBorderRot));

        final Vector3 depthSideBorderRot = new Vector3(0, 90);
        final Vector<RenderObject> depthBorder = new Vector<>();
        depthBorder.addElement(new RenderObject(depthSideBorder, new Vector3(22, 5), depthSideBorderRot));
        depthBorder.addElement(new RenderObject(depthSideBorder, new Vector3(20, 5), depthSideBorderRot));
        depthBorder.addElement(new RenderObject(depthSideBorder, new Vector3(-20, 5), depthSideBorderRot));
        depthBorder.addElement(new RenderObject(depthSideBorder, new Vector3(-22, 5), depthSideBorderRot));

        final Vector3 depthTopBorderRot = new Vector3(90, 0);
        depthBorder.addElement(new RenderObject(depthTopBorder, new Vector3(21, 6, 0), depthTopBorderRot));
        depthBorder.addElement(new RenderObject(depthTopBorder, new Vector3(-21, 6, 0), depthTopBorderRot));

        final RenderObject tableLegTmp = createTableLeg(black);
        final RenderObject tableLeg1 = tableLegTmp.clone();
        tableLeg1.setPosition(new Vector3(19, 0, 7));
        final RenderObject tableLeg2 = tableLegTmp.clone();
        tableLeg2.setPosition(new Vector3(-19, 0, 7));
        final RenderObject tableLeg3 = tableLegTmp.clone();
        tableLeg3.setPosition(new Vector3(19, 0, -7));
        final RenderObject tableLeg4 = tableLegTmp.clone();
        tableLeg4.setPosition(new Vector3(-19, 0, -7));
        final Vector<RenderObject> tableLegs = new Vector<>();
        tableLegs.addElement(tableLeg1);
        tableLegs.addElement(tableLeg2);
        tableLegs.addElement(tableLeg3);
        tableLegs.addElement(tableLeg4);

        result.addChildren(fieldObject, backFieldObject);
        result.addChildren(widthBorder);
        result.addChildren(depthBorder);
        result.addChildren(tableLegs);

        return result;
    }

    private static RenderObject createTableLeg(Material material) {
        final RenderObject result = new RenderObject();
        final Rectangle rect = new Rectangle(1, 8, material);

        final RenderObject plane1 = new RenderObject(new Vector3(0, 0, 0.5));
        plane1.shape = rect;

        final RenderObject plane2 = new RenderObject(new Vector3(0, 0, -0.5));
        plane2.shape = rect;

        final RenderObject plane3 = new RenderObject(new Vector3(0.5, 0, 0), new Vector3(0, 90));
        plane3.shape = rect;

        final RenderObject plane4 = new RenderObject(new Vector3(-0.5, 0, 0), new Vector3(0, 90));
        plane4.shape = rect;

        result.addChildren(plane1, plane2, plane3, plane4);

        return result;
    }

    private static RenderObject createBalls() {
        final RenderObject result = new RenderObject(new Vector3(0, 5));

        double radius = 0.8;
        final Material blackBall = new Material(Color.black(), 2);
        final Material whiteBall = new Material(Color.white(), 3);

        final Vector<RenderObject> balls = new Vector<>();
        balls.addElement(new RenderObject(new Sphere(radius, blackBall),
                new Vector3(-10, /*radius * */0.5)));
        
        balls.addElement(new RenderObject(new Sphere(radius, whiteBall),
                new Vector3(8, /*radius * */0.5)));
        
        balls.addElement(new RenderObject(new Sphere(radius, whiteBall),
                new Vector3(9, /*radius * */0.5, 0.5/* * radius*/)));
        balls.addElement(new RenderObject(new Sphere(radius, whiteBall),
                new Vector3(9, /*radius * */0.5, -0.5/* * radius*/)));
        
        balls.addElement(new RenderObject(new Sphere(radius, whiteBall),
                new Vector3(10, /*radius * */0.5, 1.0/* * radius*/)));
        balls.addElement(new RenderObject(new Sphere(radius, whiteBall),
                new Vector3(10, /*radius * */0.5)));
        balls.addElement(new RenderObject(new Sphere(radius, whiteBall),
                new Vector3(10, /*radius * */0.5, -1.0/* * radius*/)));
        
        balls.addElement(new RenderObject(new Sphere(radius, whiteBall),
                new Vector3(11, /*radius * */0.5, 1.5/* * radius*/)));
        balls.addElement(new RenderObject(new Sphere(radius, whiteBall),
                new Vector3(11, /*radius * */0.5, 0.5/* * radius*/)));
        balls.addElement(new RenderObject(new Sphere(radius, whiteBall),
                new Vector3(11, /*radius * */0.5, -0.5/* * radius*/)));
        balls.addElement(new RenderObject(new Sphere(radius, whiteBall),
                new Vector3(11, /*radius * */0.5, -1.5/* * radius*/)));
        
        balls.addElement(new RenderObject(new Sphere(radius, whiteBall),
                new Vector3(12, /*radius * */0.5, 2.0/* * radius*/)));
        balls.addElement(new RenderObject(new Sphere(radius, whiteBall),
                new Vector3(12, /*radius * */0.5, 1.0/* * radius*/)));
        balls.addElement(new RenderObject(new Sphere(radius, whiteBall),
                new Vector3(12, /*radius * */0.5)));
        balls.addElement(new RenderObject(new Sphere(radius, whiteBall),
                new Vector3(12, /*radius * */0.5, -1.0/* * radius*/)));
        balls.addElement(new RenderObject(new Sphere(radius, whiteBall),
                new Vector3(12, /*radius * */0.5, -2.0/* * radius*/)));

        result.addChildren(balls);

        return result;
    }
}
