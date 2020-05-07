/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.graphics.opengl;

import my.graphics.opengl.utils.Vector3;
import org.lwjgl.Version;

/**
 *
 * @author Абс0лютный Н0ль
 */
public class App {

    public static void main(String[] args) {
        System.out.println(Version.getVersion());
        new Window().run();
        System.out.println(new Vector3<>(1, 0, 1).toString());
    }

}
