package org.loy.multithread.basic.yield;

import org.junit.Test;

public class TestYield implements Runnable {
    
    public Object lock = null;

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // synchronized(lock) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            // Thread.yield();
            }
        // }
    }

    @Test
    public void testYield() {
        TestYield runn = new TestYield();
        runn.lock = new Object();
        Thread t1 = new Thread(runn,"FirstThread");
        Thread t2 = new Thread(runn,"SecondThread");

        t1.start();
        t2.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
