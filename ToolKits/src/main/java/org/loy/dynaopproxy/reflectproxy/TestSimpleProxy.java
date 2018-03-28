package org.loy.dynaopproxy.reflectproxy;

import java.lang.reflect.Proxy;

import org.loy.beans.ISimpleAopService;

public class TestSimpleProxy {

    public static void main(String[] args) {

        System.out.println("Proxy.newProxyInstance");
        ISimpleAopService proxy = (ISimpleAopService)Proxy.newProxyInstance(
            Thread.currentThread().getContextClassLoader(),
            new Class<?>[] {ISimpleAopService.class},
            new SimpleProxy());

        proxy.helloWorld();

    }

}
