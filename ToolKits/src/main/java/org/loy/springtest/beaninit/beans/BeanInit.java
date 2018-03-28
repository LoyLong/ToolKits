package org.loy.springtest.beaninit.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BeanInit implements InitializingBean, BeanPostProcessor, BeanNameAware, BeanFactoryAware, ApplicationContextAware,
    DisposableBean {

    public void test() {
        System.out.println("[BeanInit].application method test");
    }

    public BeanInit() {
        super();
        System.out.println("[BeanInit].init");
    }

    public void init2() {
        System.out.println("[BeanInit].init2");
    }

    public void destroy() throws Exception {
        System.out.println("[BeanInit].destroy");
    }

    public void destroy2() throws Exception {
        System.out.println("[BeanInit].destroy2");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("[BeanInit].setApplicationContext");
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("[BeanInit].setBeanFactory");
    }

    public void setBeanName(String name) {
        System.out.println("[BeanInit].setBeanName, name = " + name);
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[BeanInit].postProcessBeforeInitialization, bean = " + bean + ", beanName = " + beanName);
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[BeanInit].postProcessAfterInitialization, bean = " + bean + ", beanName = " + beanName);
        return bean;
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("[BeanInit].afterPropertiesSet");
    }

}
