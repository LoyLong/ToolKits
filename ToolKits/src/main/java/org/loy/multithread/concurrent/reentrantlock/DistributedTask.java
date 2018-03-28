package org.loy.multithread.concurrent.reentrantlock;

public class DistributedTask {

    public int count = 0;

    public synchronized void increaseCount() {
        count++;
    }

}
