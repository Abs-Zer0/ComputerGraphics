/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.graphics.rt.scene;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import org.apache.commons.math3.geometry.euclidean.threed.Line;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

/**
 *
 * @author Абс0лютный Н0ль
 */
public class Node {

    private Node parent;
    private Vector<Node> children;
    private Vector3D position;
    private Region object;

    public Node() {
        this(null, new ArrayList<Node>());
    }

    public Node(final Node parent) {
        this(parent, Vector3D.ZERO);
    }

    public Node(final Collection<Node> children) {
        this(null, children);
    }

    public Node(final Node parent, final Collection<Node> children) {
        this(parent, children, Vector3D.ZERO);
    }

    public Node(final Node parent, final Vector3D pos) {
        this(parent, new ArrayList<Node>(), pos);
    }

    public Node(final Node parent, final Collection<Node> children, final Vector3D pos) {
        this.parent = parent;
        this.children = new Vector<>();
        if (children != null) {
            this.children.addAll(children);
        }
        this.position = new Vector3D(1.0, pos);
        this.object = null;
    }

    public Node(final Node[] children) {
        this(null, children);
    }

    public Node(final Node parent, final Node[] children) {
        this(parent, children, Vector3D.ZERO);
    }

    public Node(final Node parent, final Node[] children, final Vector3D pos) {
        this.parent = parent;
        this.children = new Vector<>();
        if (children != null) {
            this.children.addAll(Arrays.asList(children));
        }
        this.position = new Vector3D(1.0, pos);
        this.object = null;
    }

    public final Node GetParent() {
        return this.parent;
    }

    public final Node GetRoot() {
        if (this.parent == null) {
            return this;
        }

        return this.parent.GetRoot();
    }

    public synchronized final Vector<Node> GetChildren() {
        return this.children;
    }

    public Vector3D Position() {
        return this.position;
    }

    public Region Object() {
        return this.object;
    }

    public void Add(final Node child) {
        Node par = child.parent;
        if (par != null && par != this) {
            par.Remove(child);
        }

        child.parent = this;
        this.children.add(child);
    }

    public void Remove(final Node child) {
        this.children.remove(child);
        child.parent = null;
    }

    public void RemoveAll() {
        this.children.stream().map((el) -> {
            el.parent = null;
            return el;
        }).forEachOrdered((el) -> {
            el.RemoveAll();
        });
        this.children.removeAllElements();
    }

    @Override
    public String toString() {
        return this.position.toString();
    }
}
