package org.loy.dynaopproxy.reflectproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SimpleProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object retVal = null;

        System.out.println("[SimpleProxy].[invoke].in");

        return retVal;
    }

}
