/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.graphics.opengl.utils;

import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Абс0лютный Н0ль
 * @param <T>
 */
public class Matrix<T extends Number> {

    private int cols, rows;
    private ArrayList<T> elements;

    public Matrix(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;

        this.elements = new ArrayList<>(this.cols * this.rows);
    }
}
