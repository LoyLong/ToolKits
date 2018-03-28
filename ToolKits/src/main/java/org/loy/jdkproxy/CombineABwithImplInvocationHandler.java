package org.loy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.loy.interfacetest.IA;

public class CombineABwithImplInvocationHandler implements InvocationHandler {

    private final IA target;

    public CombineABwithImplInvocationHandler(IA targetParam) {
        this.target = targetParam;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("[CombineABInvocationHandler].[invoke].in");
        return method.invoke(this.target, args);
    }

}
