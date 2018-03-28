package org.loy.jdkproxy.test;

import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

import org.loy.interfacetest.IA;
import org.loy.interfacetest.impl.CombineABwithImpl;
import org.loy.jdkproxy.CombineABwithImplInvocationHandler;
import org.springframework.util.ClassUtils;

import sun.misc.ProxyGenerator;

public class TestJDKProxy {

    public static void main(String[] args) {

        IA ab = new CombineABwithImpl(" combine AB");

        IA a = (IA) Proxy.newProxyInstance(ClassUtils.getDefaultClassLoader(), new Class[] {IA.class},
            new CombineABwithImplInvocationHandler(ab));

        a.display();

        createProxyClassFile();
    }

    public static void createProxyClassFile() {
        String name = "ProxySubject";
        byte[] data = ProxyGenerator.generateProxyClass(name, new Class[] {IA.class});
        System.out.println(String.valueOf(data));
        try {
            FileOutputStream out = new FileOutputStream(name + ".class");
            out.write(data);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
