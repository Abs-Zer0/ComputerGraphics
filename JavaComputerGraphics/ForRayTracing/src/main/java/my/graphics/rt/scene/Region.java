/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.graphics.rt.scene;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Vector;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.commons.math3.geometry.euclidean.threed.Line;

/**
 *
 * @author Абс0лютный Н0ль
 */
public class Region {

    private Vector<Primitive> primitives;

    public Region() {
        this(null);
    }

    public Region(final Primitive prim) {
        this.primitives = new Vector<>(1);
        if (prim != null) {
            this.primitives.add(prim);
        }
    }

    public void AddAll(Primitive... prims) {
        this.primitives.addAll(Arrays.asList(prims));
    }

    public Line RayIntersect(final Line ray) {
        Optional<Line> nearest = this.primitives.parallelStream()
                .flatMap(l -> l.RayIntersect(ray).parallelStream())
                .min((o1, o2) -> {
                    double d1=o1.getOrigin().distanceSq(ray.getOrigin());
                    double d2=o2.getOrigin().distanceSq(ray.getOrigin());
                    
                    return Double.compare(d1, d2); //To change body of generated lambdas, choose Tools | Templates.
                });
        
        return nearest.orElse(null);
    }
}
