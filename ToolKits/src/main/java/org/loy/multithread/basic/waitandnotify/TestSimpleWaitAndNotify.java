package org.loy.multithread.basic.waitandnotify;

import java.util.LinkedList;
import java.util.Queue;

public class TestSimpleWaitAndNotify {

    public static void main(String[] args) {
        try {
            final Queue<Integer> sharedQ = new LinkedList<Integer>();
            Thread producer = new Producer(sharedQ);
            Thread consumer = new Consumer(sharedQ);

            producer.start();
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
