package org.loy.interfacetest.impl;

import org.loy.interfacetest.IAblank;
import org.loy.interfacetest.IBblank;

public class CombineAB implements IAblank, IBblank {

    private final String message;

    public CombineAB() {
        this.message = "";
    }

    public CombineAB(String messageParam) {
        this.message = messageParam;
    }

    @Override
    public void display() {
        System.out.println("CombinAB.display : " + message);
    }

}
