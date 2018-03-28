package org.loy.springtest.lazyinit;

import java.util.Arrays;

import org.loy.springtest.lazyinit.beans.Parent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestLazyInit {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/TestSpringConfig.xml");

        Parent parent = (Parent) context.getBean("parentAls1");
        parent.setName("Parent name update");

        Parent parent2 = (Parent) context.getBean("parent");
        System.out.println("Parent.name : " + parent2.getName());

        // Parent parent3 = (Parent) context.getBean("parent2");
        // System.out.println("Parent.name : " + parent3.getName());
        String[] beanNames = context.getBeanDefinitionNames();
        System.out.println(Arrays.asList(beanNames));
        for (String beanName : beanNames) {
            System.out.println(beanName + " : " + context.getBean(beanName).getClass().toString());
        }
    }

}
