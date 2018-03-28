package org.loy.springtest.beaninit;

import org.loy.springtest.beaninit.beans.BeanInit;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBeanInit {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/beaninit/TestBeanInit.xml");

        BeanInit beanInit = context.getBean(BeanInit.class);
        beanInit.test();
        beanInit = null;

        System.out.println(" --- 2nd run --- ");

        beanInit = context.getBean(BeanInit.class);
        beanInit.test();

        context.close();
        
    }

}
