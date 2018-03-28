package org.loy.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.loy.hibernate.pojo.OrderExecution;

public class TestExec {

    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure("config/hibernate-pure/hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();

        Session session = null;
        try {
            session = factory.openSession();
            Transaction tran = session.beginTransaction();

            tran.begin();
            // OrderExecution newExec = new OrderExecution();
            // session.save(newExec);

            OrderExecution enquiryExec = session.load(OrderExecution.class, new Integer("1"));
            System.out.println("Result " + enquiryExec);
            // int resultSize =
            // session.createQuery("select * from OES02.dbo.AuxExec").getFetchSize();
            // System.out.print("resultSize " + resultSize);
            // session.getTransaction().commit();
            tran.commit();
            System.out.println("After transaction commit");
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                if (session.isOpen()) {
                    session.close();
                }
            }
        }

        System.out.println("End of processing");
    }

}
