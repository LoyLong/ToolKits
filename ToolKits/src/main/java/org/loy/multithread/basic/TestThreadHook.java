package org.loy.multithread.basic;

public class TestThreadHook {

    private static Object locker = new Object();
    private static Integer totalCount = 0;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Thread shutdownHookOne = new Thread() {
            @Override
            public void run() {
                System.out.println("shutdownHook thread ");
            }
        };
        Runtime.getRuntime().addShutdownHook(shutdownHookOne);

        Thread threadOne = new Thread() {
            int count = 0;

            @Override
            public void run() {
                System.out.println("thread 1 in ");

                try {
                    synchronized (locker) {
                        while (totalCount < 20) {
                            Thread.sleep(200);
                            count++;
                            System.out.println("thread 1 Count = " + count + ", totalCount = " + totalCount);
                            if (totalCount < 10) {
                                System.out.println("thread 1 notify ");
                                locker.notifyAll();
                                locker.wait();
                                System.out.println("thread 1 wake up ");
                            } else {
                                totalCount++;
                            }
                            System.out.println("thread 1 run " + count + " time(s)!");
                        }
                    }
                    System.exit(0);
                } catch (Exception e) {
                    System.out.println("thread 1 end with error " + count + " time(s)!");
                    e.printStackTrace();
                }
                System.out.println("thread 1 end " + count + " time(s)!");
            }
        };

        Thread threadTwo = new Thread() {
            int count = 0;

            @Override
            public void run() {
                System.out.println("thread 2 in ");

                try {
                    synchronized (locker) {
                        while (totalCount < 20) {
                            Thread.sleep(200);
                            count++;
                            System.out.println("thread 2 Count = " + count + ", totalCount = " + totalCount);
                            if (totalCount < 10) {
                                totalCount++;
                            } else {
                                System.out.println("thread 2 notify ");
                                locker.notifyAll();
                                locker.wait();
                                System.out.println("thread 2 wake up ");
                            }
                            System.out.println("thread 2 run " + count + " time(s)!");
                        }
                    }
                    System.exit(0);
                } catch (Exception e) {
                    System.out.println("thread 2 end with error " + count + " time(s)!");
                    e.printStackTrace();
                }
                System.out.println("thread 2 end " + count + " time(s)!");
            }
        };

        // threadOne.run();
        // threadTwo.run();
        threadOne.start();
        threadTwo.start();
    }
}
