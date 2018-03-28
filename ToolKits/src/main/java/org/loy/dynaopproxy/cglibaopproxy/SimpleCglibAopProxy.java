package org.loy.dynaopproxy.cglibaopproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class SimpleCglibAopProxy extends Proxy {

    protected SimpleCglibAopProxy(InvocationHandler h) {
        super(h);
    }

}
