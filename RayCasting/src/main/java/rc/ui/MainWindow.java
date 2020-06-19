/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc.ui;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import rc.RayCaster;
import rc.scene.Scene;

/**
 *
 * @author ���0������ �0��
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        this.renderer = new RayCaster(scene, 10);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ray casting");
        setMinimumSize(new java.awt.Dimension(800, 480));
        setName("mainWindow"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1280, 720));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                WindowResized(evt);
            }
        });
		
		this.label = new JLabel(new ImageIcon(this.renderer.render(getPreferredSize().width, getPreferredSize().height)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

		add(this.label);
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void WindowResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_WindowResized
        // TODO add your handling code here:
        this.label.setSize(this.getSize().width, this.getSize().height);
    }//GEN-LAST:event_WindowResized

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private JLabel label;
    private final Scene scene = Scene.createExampleScene();
    private final RayCaster renderer;
}
