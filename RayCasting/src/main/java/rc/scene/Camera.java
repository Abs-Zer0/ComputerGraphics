/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.scene;

import rc.math.Angle;
import rc.math.Ray;
import rc.math.Vector3;

/**
 *
 * @author Абс0лютный Н0ль
 *
 * Класс камеры
 */
public class Camera {

    public final Transform transform;
    public final Integer width;
    public final Integer height;
    public double far;

    private double fov;
    private double localFar;

    /**
     * Создаёт экземпляр класса
     *
     * @param transform положение наблюдателя
     * @param width кол-во пикселей модели экрана по горизонтали
     * @param height кол-во пикселей модели экрана по вертикали
     * @param fov угол обзора
     * @param far дальность прорисовки
     */
    public Camera(Transform transform, int width, int height, double fov, double far) {
        this.transform = transform != null ? transform : Transform.zero();

        this.width = width > 0 ? width : 800;
        this.height = height > 0 ? height : 480;

        setFov(fov);

        this.far = far > 0 ? far : 100;
    }

    /**
     * Создаёт экземпляр класса
     *
     * @param transform положение наблюдателя
     * @param width кол-во пикселей модели экрана по горизонтали
     * @param height кол-во пикселей модели экрана по вертикали
     * @param fov угол обзора
     */
    public Camera(Transform transform, int width, int height, double fov) {
        this(transform, width, height, fov, 100.0);
    }

    /**
     * Создаёт экземпляр класса
     *
     * @param transform положение наблюдателя
     * @param width кол-во пикселей модели экрана по горизонтали
     * @param height кол-во пикселей модели экрана по вертикали
     */
    public Camera(Transform transform, int width, int height) {
        this(transform, width, height, 90.0);
    }

    /**
     * Создаёт экземпляр класса
     *
     * @param width кол-во пикселей модели экрана по горизонтали
     * @param height кол-во пикселей модели экрана по вертикали
     * @param fov угол обзора
     * @param far дальность прорисовки
     */
    public Camera(int width, int height, double fov, double far) {
        this(Transform.zero(), width, height, fov, far);
    }

    /**
     * Создаёт экземпляр класса
     *
     * @param width кол-во пикселей модели экрана по горизонтали
     * @param height кол-во пикселей модели экрана по вертикали
     * @param fov угол обзора
     */
    public Camera(int width, int height, double fov) {
        this(width, height, fov, 100.0);
    }

    /**
     * Создаёт экземпляр класса
     *
     * @param width кол-во пикселей модели экрана по горизонтали
     * @param height кол-во пикселей модели экрана по вертикали
     */
    public Camera(int width, int height) {
        this(width, height, 90.0);
    }

    /**
     * Создаёт базовый экземпляр класса
     */
    public Camera() {
        this(800, 480);
    }

    /**
     * Угол обзора
     */
    public double getFov() {
        return this.fov;
    }

    /**
     * Назначение угла обзора
     */
    public void setFov(double fov) {
        if (fov >= 180.0) {
            this.fov = fov % 180.0;
        } else if (fov < 0) {
            this.fov = 180.0 - fov;
        } else {
            this.fov = fov;
        }

        double tg = Math.tan(Angle.getRadians(0.5 * this.fov));
        this.localFar = Math.sqrt(this.width * this.width + this.height * this.height) / tg;
    }

    /**
     * Создание луча от положения наблюдателя через точки модели экрана x и y
     */
    public Ray castRay(double x, double y) {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
            return Ray.zero();
        }

        double dirX = x - 0.5 * width.doubleValue();
        double dirY = 0.5 * height.doubleValue() - y;
        Vector3 direction = new Vector3(dirX, dirY, localFar).multi(this.transform.getRotationMatrix());

        return new Ray(this.transform.getLocalPosition(), direction);
    }

}
