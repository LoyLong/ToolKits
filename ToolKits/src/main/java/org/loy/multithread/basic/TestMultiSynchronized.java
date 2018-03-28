package org.loy.multithread.basic;

public class TestMultiSynchronized {

    private final Object lock = new Object();

    public static void main(String[] args) {
        new TestMultiSynchronized().testLock();
        new TestMultiSynchronized().testNpLock();
    }

    public void testLock() {
        long costTime = System.currentTimeMillis();
        synchronized (lock) {

            System.out.println("Lock 1st time");

            synchronized (lock) {

                System.out.println("Lock 2nd time");
            }

        }
        costTime = System.currentTimeMillis() - costTime;

        System.out.println("Lock cost : " + costTime + " millis");
    }

    public void testNpLock() {
        long costTime = System.currentTimeMillis();

        System.out.println("Lock 1st time");
        System.out.println("Lock 2nd time");
        costTime = System.currentTimeMillis() - costTime;

        System.out.println("NonLock cost : " + costTime + " millis");
    }

}
