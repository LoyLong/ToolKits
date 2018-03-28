package org.loy.multithread.basic;

public class TestWaitNotify implements Runnable {

    private final int num;
    private final Object lock;

    public TestWaitNotify(int num, Object lock) {
        super();
        this.num = num;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(200);
                synchronized (lock) {
                    lock.notifyAll();
                    lock.wait();
                    System.out.println("Thread " + num + " wake up!");
                }
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        final Object lock = new Object();

        Thread thread1 = new Thread(new TestWaitNotify(1, lock));
        Thread thread2 = new Thread(new TestWaitNotify(2, lock));

        thread1.start();
        thread2.start();
    }


}
