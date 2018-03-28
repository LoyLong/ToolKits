package org.loy.test2;

/**
 * <p>
 * <b> Insert description of the class's responsibility/role. </b>
 * </p>
 */
public class TestThread implements Runnable {

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        // TODO Auto-generated method stub
        System.out.println("Test");
    }

    public static void main(final String[] args) {
        Thread t = new Thread(new TestThread());
        t.run();
        t.run();
        t.start();
    }
}
