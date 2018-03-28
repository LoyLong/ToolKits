package org.loy.springtest.lazyinit.beans;

import java.io.Serializable;

public class Parent implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private ChildA child = null;

    private String name = null;

    public Parent() {
        System.out.println("Parent.init");
    }

    public ChildA getChild() {
        return child;
    }

    public void setChild(ChildA child) {
        this.child = child;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
