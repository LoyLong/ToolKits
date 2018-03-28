package org.loy.interfacetest.impl;

import org.loy.interfacetest.IA;
import org.loy.interfacetest.IB;

public class CombineABwithImpl implements IA, IB {

    private final String message;

    public CombineABwithImpl() {
        this.message = "";
    }

    public CombineABwithImpl(String messageParam) {
        this.message = messageParam;
    }

    @Override
    public void display() {
        System.out.println("CombineABwithImpl.display : " + message);
    }

}
