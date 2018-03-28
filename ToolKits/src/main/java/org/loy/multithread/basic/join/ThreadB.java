package org.loy.multithread.basic.join;

public class ThreadB implements Runnable {

    String msg;

    public ThreadB(String msg) {
        super();
        this.msg = msg;
    }

    @Override
    public void run() {
        System.out.println(System.currentTimeMillis() + " : " + msg + " starts");
        System.out.println(System.currentTimeMillis() + " : " + msg + " ends");
    }

}
