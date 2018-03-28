package org.loy.multithread.basic;

public class TestThreadWait implements Runnable {
    int number = 10;

    public void firstMethod() throws Exception {
        System.out.println("in firstMethod");
        synchronized (this) {
            number += 100;
        }
    }

    public void secondMethod() throws Exception {
        System.out.println("in secondMethod");
        synchronized (this) {
            // Thread.sleep(2000);
            this.wait(2000);
            number *= 200;
        }
    }

    @Override
    public void run() {
        try {
            firstMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        TestThreadWait threadTest = new TestThreadWait();
        Thread thread = new Thread(threadTest);
        thread.start();
        threadTest.secondMethod();
        System.out.println(threadTest.number);
    }

}
