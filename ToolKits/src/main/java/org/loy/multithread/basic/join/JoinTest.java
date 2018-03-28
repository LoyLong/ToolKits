package org.loy.multithread.basic.join;

import org.junit.Test;

public class JoinTest {

    @Test
    public void test() {
        Thread a = new Thread(new ThreadA("Thread A"));
        Thread b = new Thread(new ThreadB("Thread B"));
        System.out.println(System.currentTimeMillis() + " : Join test starts");
        try {

            a.start();
            System.out.println(System.currentTimeMillis() + " : Thread A join in main");
            a.join();
            b.start();
            System.out.println(System.currentTimeMillis() + " : Thread B join in main");
            b.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(System.currentTimeMillis() + " : Join test ends");
    }

}
