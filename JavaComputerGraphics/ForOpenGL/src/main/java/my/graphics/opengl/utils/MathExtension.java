/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.graphics.opengl.utils;

/**
 *
 * @author ���0������ �0��
 */
public class MathExtension {

    public static final double DegreesToPI = Math.PI / 180.0;

    public static double cos(double degrees) {
        return Math.cos(DegreesToPI * degrees);
    }

    public static double sin(double degrees) {
        return Math.sin(DegreesToPI * degrees);
    }
}
