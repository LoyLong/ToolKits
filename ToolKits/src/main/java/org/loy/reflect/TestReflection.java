package org.loy.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;
import org.loy.reflect.beans.ReflectObject;


public class TestReflection {

    @Test
    public void testClassNewInstance() {

        Class target = null;
        try {
            target = Class.forName("org.loy.reflect.beans.ReflectObject");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        ReflectObject objWthDfltCstr = null;
        try {
            objWthDfltCstr = (ReflectObject) target.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        objWthDfltCstr.printInfo();

        Method mth = null;
        try {
            mth = target.getDeclaredMethod("printInfo", null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        try {
            mth.invoke(objWthDfltCstr, null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testConstructorNewInstance() {
        Class trgtCls = null;
        try {
            trgtCls = ClassLoader.getSystemClassLoader().loadClass("org.loy.reflect.beans.ReflectObject");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Constructor strctr = null;
        try {
            strctr = trgtCls.getConstructor(String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        Object trgtObj = null;
        try {
            trgtObj = strctr.newInstance("Init with constructor");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        Method prntMth = null;
        Method setNmMth = null;
        Method setSqMth = null;
        try {
            prntMth = trgtCls.getDeclaredMethod("printInfo", null);
            setNmMth = trgtCls.getDeclaredMethod("setName", String.class);
            setSqMth = trgtCls.getDeclaredMethod("setSequence", Integer.TYPE);// int.class
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        try {
            prntMth.invoke(trgtObj, null);
            setNmMth.invoke(trgtObj, "Updated by setter");
            setSqMth.invoke(trgtObj, 1);
            prntMth.invoke(trgtObj, null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

}
