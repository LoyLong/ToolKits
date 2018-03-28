package org.loy.interfacetest.test;

import org.loy.interfacetest.IA;
import org.loy.interfacetest.IAblank;
import org.loy.interfacetest.IB;
import org.loy.interfacetest.impl.CombineAB;
import org.loy.interfacetest.impl.CombineABwithImpl;

public class TestInterfaces {

    public static void main(String[] args) {

        IA a = new CombineABwithImpl();
        a.display();

        IB b = (IB) a;
        b.display();

        // IA a2 = new IA() {};
        // a2.display();

        IAblank ab = new CombineAB();
        ab.display();
        // ((IB) (b.getClass().getInterfaces()[1])).displayStatic();
    }

}
