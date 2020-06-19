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
 * @author јбс0лютный Ќ0ль
 * 
 *  ласс объекта, который необходимо отрисовать
 */
public class RenderObject extends Transform {

    public Shape shape;

    /**
    * —оздаЄт экземпл€р класса с заданной фигурой - shape, позицией - pos, поворотом - rot,
    * списком дочерних объектов - children
    */
    public RenderObject(Shape shape, Vector3 pos, Vector3 rot, Collection<? extends Transform> children) {
        super(pos, rot, children);
        this.shape = shape;
    }

    /**
    * —оздаЄт экземпл€р класса с заданной фигурой - shape, позицией - pos, поворотом - rot
    */
    public RenderObject(Shape shape, Vector3 pos, Vector3 rot) {
        this(shape, pos, rot, null);
    }

    /**
    * —оздаЄт экземпл€р класса с заданной фигурой - shape, позицией - pos
    */
    public RenderObject(Shape shape, Vector3 pos) {
        this(shape, pos, null);
    }

    /**
    * —оздаЄт экземпл€р класса с заданной фигурой - shape
    */
    public RenderObject(Shape shape) {
        this(shape, null);
    }

    /**
    * —оздаЄт экземпл€р класса с заданной позицией - pos, поворотом - rot,
    * списком дочерних объектов - children
    */
    public RenderObject(Vector3 pos, Vector3 rot, Collection<? extends Transform> children) {
        super(pos, rot, children);
    }

    /**
    * —оздаЄт экземпл€р класса с заданной позицией - pos, поворотом - rot
    */
    public RenderObject(Vector3 pos, Vector3 rot) {
        this(pos, rot, null);
    }

    /**
    * —оздаЄт экземпл€р класса с заданной позицией - pos
    */
    public RenderObject(Vector3 pos) {
        this(pos, null);
    }

    /**
    * —оздаЄт базовый экземпл€р класса 
    */
    public RenderObject() {
        this(Vector3.zero(), null);
    }

    /**
    * ‘ункци€ вычислени€ пересечени€ луча с объектом
    * ¬озвращает результаты, необходимые дл€ дальнейших рассчЄтов
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
}
