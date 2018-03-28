package org.loy.multithread.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

public class TestCountDownService {

    private static class CountDownService implements Runnable {
        private final String name;
        private final int timeToStart;
        private final CountDownLatch latch;

        public CountDownService(String name, int timeToStart, CountDownLatch latch) {
            this.name = name;
            this.timeToStart = timeToStart;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(timeToStart);
            } catch (InterruptedException ex) {
                Logger.getLogger(CountDownService.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(name + " is Up");
            latch.countDown(); // reduce count of CountDownLatch by 1
        }
    }


    @Test
    public void test() {

        final CountDownLatch latch = new CountDownLatch(3);
        Thread cacheService = new Thread(new CountDownService("CacheService", 1000, latch));
        Thread alertService = new Thread(new CountDownService("AlertService", 1000, latch));
        Thread validationService = new Thread(new CountDownService("ValidationService", 1000, latch));

        cacheService.start(); // separate thread will initialize CacheService
        alertService.start(); // another thread for AlertService initialization
        validationService.start();

        // application should not start processing any thread until all service
        // is up
        // and ready to do there job.
        // Countdown latch is idle choice here, main thread will start with
        // count 3
        // and wait until count reaches zero. each thread once up and read will
        // do
        // a count down. this will ensure that main thread is not started
        // processing
        // until all services is up.
        // count is 3 since we have 3 Threads (Services)
        try {
            System.out.println("CountDownLatch waiting...");
            // main thread is waiting on CountDownLatch to finish
            latch.await();
            System.out.println("All services are up, Application is starting now");
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }


    }

}
