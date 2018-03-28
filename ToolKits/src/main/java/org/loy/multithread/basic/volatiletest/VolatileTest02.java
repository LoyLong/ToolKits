package org.loy.multithread.basic.volatiletest;

public class VolatileTest02 {
    volatile int i;

    public void addI() {
        i = i + 1;
    }

    public static void main(String[] args) throws InterruptedException {
        final VolatileTest02 test01 = new VolatileTest02();
        for (int n = 0; n < 1000; n++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test01.addI();
                }
            }).start();
        }

        Thread.sleep(10000);

        System.out.println(test01.i);
    }
}