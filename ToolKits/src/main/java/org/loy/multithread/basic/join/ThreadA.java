package org.loy.multithread.basic.join;

public class ThreadA implements Runnable {

    String msg;

    public ThreadA(String msg) {
        super();
        this.msg = msg;
    }

    @Override
    public void run() {
        Thread b = new Thread(new ThreadB("Thread B under A"));
        try {
            System.out.println(System.currentTimeMillis() + " : " + msg + " starts");
            b.start();
            System.out.println(System.currentTimeMillis() + " : Thread B join in A");
            b.join();
            System.out.println(System.currentTimeMillis() + " : " + msg + " ends");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
