package org.loy.dynaopproxy.jdkaopproxy;

import java.lang.reflect.Proxy;

import org.loy.beans.ISimpleAopService;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AdvisorChainFactory;
import org.springframework.aop.framework.DefaultAdvisorChainFactory;
import org.springframework.aop.framework.ProxyFactory;

public class TestJDKAopProxy {

    public static void main(String[] args) {
        AdvisedSupport advice = new ProxyFactory();
        // advice.setFrozen(Boolean.FALSE);
        // advice.setOpaque(Boolean.FALSE);
        // advice.setOptimize(Boolean.FALSE);
        // advice.setPreFiltered(Boolean.FALSE);

        advice.setInterfaces(ISimpleAopService.class);

        AdvisorChainFactory advisorChainFactory = new DefaultAdvisorChainFactory();
        advice.setAdvisorChainFactory(advisorChainFactory);
        //DefaultPointcutAdvisor defaultAdvisor = new DefaultPointcutAdvisor();
        //advice.addAdvisor(defaultAdvisor);

        advice.addAdvice(new SimpleJDKMethodInterceptor());

        System.out.println("Proxy.newProxyInstance");
        ISimpleAopService proxy = (ISimpleAopService)Proxy.newProxyInstance(
            Thread.currentThread().getContextClassLoader(),
            // ClassUtils.getDefaultClassLoader(),
            // SimpleAopServiceImpl.class.getInterfaces(),
            new Class<?>[] {ISimpleAopService.class},
            new SimpleJDKAopProxy(advice));

        proxy.helloWorld();
        System.out.println(proxy.getClass());
        System.out.println(Proxy.isProxyClass(proxy.getClass()));

        System.out.println("ProxyFactory.getProxy");
        ISimpleAopService proxy2 = ProxyFactory.getProxy(ISimpleAopService.class, new SimpleJDKMethodInterceptor());
        proxy2.helloWorld();
        System.out.println(proxy2.getClass());
        System.out.println(proxy == proxy2);
    }

}
