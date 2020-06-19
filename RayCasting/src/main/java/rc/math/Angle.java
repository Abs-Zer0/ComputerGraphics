/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.math;

/**
 *
 * @author ���0������ �0��
 *
 * ����� ��� ������ � ������
 */
public class Angle {

    /**
    * �������������� ���� �� �������� � �������
     */
    public static double getRadians(double deg) {
        return (Constants.degToPi * deg) % Constants.dPI;
    }

    /**
    * �������������� ���� �� �������� � �������
     */
    public static double getDegrees(double rad) {
        return (Constants.PiToDeg * rad) % 360.0;
    }
}
