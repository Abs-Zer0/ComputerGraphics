/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.geometry;

import rc.math.Constants;
import rc.math.Ray;
import rc.math.RayType;
import rc.math.Vector3;
import rc.scene.Transform;

/**
 *
 * @author ���0������ �0��
 * 
 * ����� ������ �����
 */
public class Sphere extends Shape {
    
    private double radius = 0;
    
    /**
    * ������ ��������� ������ � ��������� �������� - radius, ���������� - material
    */
    public Sphere(double radius, Material material) {
        super(material);
        setRadius(radius);
    }
    
    /**
    * ������ ��������� ������ � �������� �������� - radius
    */
    public Sphere(double radius) {
        this(radius, null);
    }
    
    /**
    * ������ ��������� ������ � �������� ���������� - material
    */
    public Sphere(Material material) {
        this(0.0, material);
    }
    
    /**
    * ������ �����
    */
    public double getRadius() {
        return this.radius;
    }
    
    /**
    * ���������� ������� �����
    * ���������� �������� ������������� ������
    */
    public double setRadius(double radius) {
        this.radius = radius < 0.0 ? 0.0 : radius;
        
        return this.radius;
    }
    
    /**
    * ������� ���������� ����������� ���� ray �� ������ � �������� ������� transform
    * ���������� ����������, ����������� ��� ���������� ���������
    */
    @Override
    public IntersectResult isIntersected(Ray ray, Transform transform) {
        final Ray _ray = ray;
        final Transform _transform = transform;
        
        final Double scale = 1.0 / _ray.getDirection().sqrLength();
        if (scale.isInfinite()) {
            return IntersectResult.miss();
        }
        
        final Vector3 deltaPosition = _ray.getOrigin().subtract(_transform.getGlobalPostion());
        double k = _ray.getDirection().product(deltaPosition) * scale;
        double c = (deltaPosition.sqrLength() - this.radius * this.radius) * scale;
        final Double D = Math.sqrt(k * k - c);
        if (D.isNaN()) {
            return IntersectResult.miss();
        }
        
        double t1 = -k - D;
        double t2 = -k + D;
        
        if (t1 > Constants.tolerance && t2 > Constants.tolerance) {
            double min = Math.min(t1, t2);
            final Vector3 originNormal = _ray.getOrigin().add(_ray.getDirection().product(min));
            final RayType rayType = Math.abs(t1 - t2) < Constants.tolerance ? RayType.TANGENT : RayType.IN;
            final Ray normal = new Ray(originNormal, originNormal.subtract(_transform.getGlobalPostion()));
            
            return new IntersectResult(this, normal, rayType, min);
        } else {
            double max = Math.max(t1, t2);
            if (max > Constants.tolerance) {
                final Vector3 originNormal = _ray.getOrigin().add(_ray.getDirection().product(max));
                final Ray normal = new Ray(originNormal, originNormal.subtract(_transform.getGlobalPostion()));
                
                return new IntersectResult(this, normal, RayType.OUT, max);
            } else {
                
                return IntersectResult.miss();
            }
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
        
        Sphere sph = (Sphere) obj;
        return this.radius == sph.radius;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.radius) ^ (Double.doubleToLongBits(this.radius) >>> 32));
        return hash;
    }
    
}
