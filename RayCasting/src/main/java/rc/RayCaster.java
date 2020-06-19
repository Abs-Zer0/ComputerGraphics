/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.stream.StreamSupport;
import rc.geometry.IntersectResult;
import rc.math.*;
import rc.scene.*;
import rc.scene.lights.*;

/**
 *
 * @author Абс0лютный Н0ль
 *
 * Класс рендер-машины, которая рендерит выбранную сцену
 */
public class RayCaster {

    private Scene scene;
    private int depth = 10;
    private int superSample = 1;
    private RenderObject[] shapes;
    private Light[] lights;

    /**
     *
     * @param scene Сцена для рендеринга
     * @param depth Глубина рекурсии
     * @param superSample Значение суперсэмплинга
     */
    public RayCaster(Scene scene, int depth, int superSample) {
        this.scene = scene;
        setRecursionDepth(depth);
        setSuperSampling(superSample);
    }

    /**
     * Создаёт экземпляр класса с сценой для рендера - scene, глубиной рекурсии
     * - depth
     */
    public RayCaster(Scene scene, int depth) {
        this(scene, depth, 1);
    }

    /**
     * Создаёт экземпляр класса с сценой для рендера - scene
     */
    public RayCaster(Scene scene) {
        this(scene, 10);
    }

    /**
     * Глубина рекурсии
     */
    public int getRecursionDepth() {
        return this.depth;
    }

    /**
     * Назначение глубины рекурсии Возвращает реально установленную глубину
     * рекурсии, 1 если было передано значение < 1
     */
    public int setRecursionDepth(int value) {
        this.depth = depth < 1 ? 1 : depth;

        return this.depth;
    }

    /**
     *
     * @return Значение суперсэмплинга
     */
    public int getSuperSampling() {
        return this.superSample;
    }

    /**
     *
     * @param value Новое значение суперсэмплинга, не может быть < 1
     * @return Реально установленное значение суперсэмплинга
     */
    public int setSuperSampling(int value) {
        this.superSample = value < 1 ? 1 : value;

        return this.superSample;
    }

    /**
     * Функция рендеринга сцены Возвращает отрендеренное изображение
     */
    public BufferedImage render(int width, int height) {
        this.shapes = this.scene.getFlattenObjects().parallelStream()
                .filter(obj -> obj instanceof RenderObject)
                .map(obj -> (RenderObject) obj)
                .toArray(RenderObject[]::new);
        this.lights = this.scene.getFlattenObjects().parallelStream()
                .filter(obj -> obj instanceof Light)
                .map(obj -> (Light) obj)
                .toArray(Light[]::new);

        final Camera currentCamera = this.scene.getCamera();
        final BufferedImage result = new BufferedImage(currentCamera.width,
                currentCamera.height,
                BufferedImage.TYPE_INT_RGB);
        final Color I = this.scene.I();

        int camWidth = currentCamera.width;
        int camHeight = currentCamera.height;
        int countPixels = camWidth * camHeight;
        for (int j = 0; j < camHeight; j++) {
            for (int i = 0; i < camWidth; i++) {
                double progress = (double) (j * camWidth + i) / (double) countPixels;
                System.out.println(String.format("Progress: %.2f", progress * 100.0) + "%");

                Color color = I.product(0.3);
                if (this.superSample > 1) {
                    double step = 1.0 / (double) this.superSample;
                    double x = i, y = j;

                    color = formula(currentCamera.castRay(x, y),
                            currentCamera.far,
                            this.depth);
                    x += step;
                    y += step;

                    for (int k = 0; k < this.superSample; k++) {
                        color = color.blend(formula(currentCamera.castRay(x, y),
                                currentCamera.far,
                                this.depth));
                        x += step;
                        y += step;
                    }
                } else {
                    color = color.add(formula(currentCamera.castRay(i, j),
                            currentCamera.far,
                            this.depth));
                }

                result.setRGB(i, j, color.getRGB());
            }
        }

        if (camWidth != width || camHeight != height) {
            return resizedImage(result, width, height);
        } else {
            return result;
        }
    }

    /**
     * Сложная формула вычисления цвета, принесённого лучом
     */
    private Color formula(Ray ray, double far, int depth) {
        if (depth < 0) {
            return Color.black();
        }

        final Ray _ray = ray;

        final Optional<IntersectResult> inRes = StreamSupport.stream(Arrays.spliterator(this.shapes), true)
                .map(shape -> shape.isIntersected(_ray))
                .filter(ir -> !ir.isMiss() && ir.getDistance() < far)
                .min(Comparator.comparingDouble(rights -> rights.getDistance()));

        if (inRes.isPresent()) {
            final IntersectResult target = inRes.get();

            final Color materialColor = target.getShape().getMaterial().getColor();
            double materialPhong = target.getShape().getMaterial().getPhong();
            double materialReflection = target.getShape().getMaterial().getReflection();
            double materialRefraction = target.getShape().getMaterial().getRefraction();

            double reflect_sub_alpha = materialReflection - materialColor.getAlpha();
            if (reflect_sub_alpha > 0.0) {
                double scale = 1.0 / (reflect_sub_alpha + 1);
                materialReflection *= scale;
                materialColor.setAlpha(1.0 - materialColor.getAlpha() * scale);
            }
            Color result = materialColor.premulti();

            final ShadowRay[] shadowRays = StreamSupport.stream(Arrays.spliterator(this.lights), true)
                    .map(light -> light.makeShadowRay(target.getNormal().getOrigin()))
                    .toArray(ShadowRay[]::new);
            final Vector<Color> diffuses = new Vector<>();
            final Vector<Color> phongs = new Vector<>();
            for (ShadowRay shd : shadowRays) {
                double cos = shd.cos(target.getNormal().getDirection());
                if (cos > 0.0 && shd.lenghtRay() < shd.getLight().getIntensity()) {
                    final Color light = checkShadowRay(shd);
                    diffuses.addElement(light.product(cos));
                    if (materialPhong > 0.0) {
                        phongs.addElement(light.product(Math.pow(cos, materialPhong)));
                    }
                }
            }
            final Color diffuse = diffuses.parallelStream().reduce(Color.black(), (c1, c2) -> c1.add(c2));
            final Color phong = phongs.parallelStream().reduce(Color.black(), (c1, c2) -> c1.add(c2));
            result = result.add(diffuse).product(0.4);
            result = result.add(phong.product(0.3));

            if (materialReflection > 0.0 && target.getType() != RayType.OUT) {
                final Ray reflected = _ray.reflected(target.getNormal());

                //result = result.add(formula(reflected, far, depth - 1).product(materialReflection));
                result = result.alphaBlend(formula(reflected, far, depth - 1), materialReflection);
            }

            if (materialColor.getAlpha() < 1.0 && materialRefraction > 0.0) {
                final Ray refracted;
                if (target.getType() == RayType.TANGENT || materialRefraction == 1.0) {
                    refracted = new Ray(target.getNormal().getOrigin(), _ray.getDirection());
                } else {
                    double refraction = target.getType() == RayType.IN ? materialRefraction : 1.0 / materialRefraction;
                    refracted = _ray.refracted(target.getNormal(), refraction);
                }

                if (refracted != null) {
                    double k = (1.0 - materialColor.getAlpha());
                    //result=result.product(materialColor.getAlpha());
                    //result = result.add(formula(refracted, far, depth - 1).product(k));
                    result = result.alphaBlend(formula(refracted, far, depth - 1), k);
                }
            }

            return result;
        } else {
            return Color.black();
        }
    }

    /**
     * Функция проверки на препядствия на пути теневого луча Возвращает цвет
     * источника света - если нет препядствий, затемнённый цвет источника света
     * - если есть препядствия
     */
    private Color checkShadowRay(ShadowRay shadow) {
        final ShadowRay _shadow = shadow;

        double scale = StreamSupport.stream(Arrays.spliterator(this.shapes), true)
                .map(shape -> shape.isIntersected(_shadow.getShadowRay()))
                .filter(ir -> !ir.isMiss() && ir.getDistance() < _shadow.lenghtRay())
                .map(right -> 1.0 - right.getShape().getMaterial().getColor().getAlpha())
                .reduce(1.0, (a1, a2) -> a1 * a2);

        return _shadow.getLight().getColor().product(scale);
    }

    /**
     * Функция изменения размеров отрендеренного изображения до необходимых
     * размеров Возвращает изображение с новыми размерами
     */
    private BufferedImage resizedImage(BufferedImage src, int newWidth, int newHeight) {
        final Image tmp = src.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        final BufferedImage result = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

        final Graphics2D g2d = result.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return result;
    }
}
