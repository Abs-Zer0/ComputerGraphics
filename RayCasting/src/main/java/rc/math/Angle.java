/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.math;

/**
 *
 * @author Абс0лютный Н0ль
 *
 * Класс для работы с углами
 */
public class Angle {

    /**
    * Преобразование угла из градусов в радианы
     */
    public static double getRadians(double deg) {
        return (Constants.degToPi * deg) % Constants.dPI;
    }

    /**
    * преобразование угла из радианов в градусы
     */
    public static double getDegrees(double rad) {
        return (Constants.PiToDeg * rad) % 360.0;
    }
}
