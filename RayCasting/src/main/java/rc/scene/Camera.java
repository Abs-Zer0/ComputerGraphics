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
 * @author јбс0лютный Ќ0ль
 * 
 *  ласс камеры
 */
public class Camera{
    public final Transform transform;
    public final Integer width;
    public final Integer height;
    public double far;

    private double fov;
    private double localFar;

    /**
    * —оздаЄт экземпл€р класса с положением наблюдател€ - transform, моделью экрана - width * height,
    * углом обзора - fov, дальностью прорисовки - far
    */
    public Camera(Transform transform, int width, int height, double fov, double far) {
        this.transform = transform != null ? transform : Transform.zero();

        this.width = width > 0 ? width : 800;
        this.height = height > 0 ? height : 480;

        setFov(fov);

        this.far = far > 0 ? far : 100;
    }

    /**
    * —оздаЄт экземпл€р класса с положением наблюдател€ - transform, моделью экрана - width * height,
    * углом обзора - fov
    */
    public Camera(Transform transform, int width, int height, double fov) {
        this(transform, width, height, fov, 100.0);
    }

    /**
    * —оздаЄт экземпл€р класса с положением наблюдател€ - transform, моделью экрана - width * height
    */
    public Camera(Transform transform, int width, int height) {
        this(transform, width, height, 90.0);
    }

    /**
    * —оздаЄт экземпл€р класса с моделью экрана - width * height
    */
    public Camera(int width, int height) {
        this(Transform.zero(), width, height);
    }

    /**
    * —оздаЄт базовый экземпл€р класса 
    */
    public Camera() {
        this(800, 480);
    }

    /**
    * ”гол обзора
    */
    public double getFov() {
        return this.fov;
    }

    /**
    * Ќазначение угла обзора
    */
    public void setFov(double fov) {
        if (fov >= 180.0) this.fov = fov % 180.0;
        else if (fov < 0) this.fov = 180.0 - fov;
        else this.fov = fov;

        double tg = Math.tan(Angle.getRadians(0.5 * this.fov));
        this.localFar = Math.sqrt(this.width * this.width + this.height * this.height) / tg;
    }

    /**
    * —оздание луча от положени€ наблюдател€ через точки модели экрана x и y
    */
    public Ray castRay(double x, double y) {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) return Ray.zero();

        double dirX = x - 0.5 * width.doubleValue();
        double dirY = 0.5 * height.doubleValue() - y;
        Vector3 direction = new Vector3(dirX, dirY, localFar).multi(this.transform.getRotationMatrix());

        return new Ray(this.transform.getLocalPosition(), direction);
    }

}
