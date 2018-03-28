package org.loy.hibernate.test;

import org.hibernate.Query;
import org.hibernate.Session;
import org.loy.hibernate.dao.IUserDao;
import org.loy.hibernate.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

public class TestUser {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
            "config/hibernate-withspring/application-context-configuration.xml");

        HibernateTransactionManager tranMgr = (HibernateTransactionManager) context.getBean("transactionManager");

        Session session = tranMgr.getSessionFactory().getCurrentSession();
        Query queryResult = session.createSQLQuery("select count(*) from IFS.dbo.IFSUser");
        Object result = queryResult.list().get(0);
        System.out.println("result = " + result);

        IUserDao userDao = (IUserDao) context.getBean("userDao");

        User user = new User();
        user.setPassword("BACK002");
        // user.setUsername("0x74aff27f944b2b7b34ed8c8dba964f9d6bf98ba0441928e9e8031928729a2457b17d107f0de99b99ebf3883da1e66ee275685118d745bc52aa02b3b44f09f0c4ab29f7eabb589e869aa650a19b462c274df1884a9813088319510c68c8108efd8c662805d32ab678d8ece1ad247a9331850025423fa629b23ad59b6e2e12cc");
        userDao.validateUser(user);

        // context.
    }

}
