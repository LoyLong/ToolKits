package org.loy.reflect.beans;

public class ReflectObject {

    private int sequence;
    private String name;

    public ReflectObject() {
        super();
        this.sequence = 0;
        this.name = "Default Name";
    }

    public ReflectObject(String name) {
        super();
        this.name = name;
    }

    public void printInfo() {
        System.out.println(sequence + " : " + name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

}
