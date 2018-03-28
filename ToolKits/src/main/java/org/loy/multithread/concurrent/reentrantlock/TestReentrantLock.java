package org.loy.multithread.concurrent.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class TestReentrantLock implements Runnable {

    public ReentrantLock lock = new ReentrantLock();
    public static int i = 0;
    public DistributedTask task = new DistributedTask();

    @Override
    public void run() {
        // assert lock.getHoldCount() == 0;
        System.out.println("[TestReentrantLock].[run].in");
        lock.lock();
        int count = 0;
        while (count < 100000) {
            count++;
            i++;
        }
        System.out.println("i = " + i);
        lock.unlock();
        count = 0;
        while (count < 100000) {
            count++;
            task.increaseCount();
        }
        System.out.println("[TestReentrantLock].[run].out");
    }

    @Test
    public void testReentrantLock() {
        TestReentrantLock task = new TestReentrantLock();
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("i = " + i);
        System.out.println("task.count = " + task.task.count);
    }
}
