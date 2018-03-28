package org.loy.dynaopproxy.jdkaopproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.util.ClassUtils;

public class SimpleJDKAopProxy implements AopProxy, InvocationHandler {

    /** Config used to configure this proxy */
    private final AdvisedSupport advised;

    public SimpleJDKAopProxy(AdvisedSupport config) {
        this.advised = config;
    }

    public Object getProxy() {
        return getProxy(ClassUtils.getDefaultClassLoader());
    }

    public Object getProxy(ClassLoader classLoader) {
        Class<?>[] proxiedInterfaces = AopProxyUtils.completeProxiedInterfaces(this.advised);
        return Proxy.newProxyInstance(classLoader, proxiedInterfaces, this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object retVal = null;

        List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, null);

        for (Object interceptor : chain) {
            retVal = ((MethodInterceptor) interceptor).invoke(null);
        }

        return retVal;
    }

}
