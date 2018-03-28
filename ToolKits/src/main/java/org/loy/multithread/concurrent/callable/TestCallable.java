package org.loy.multithread.concurrent.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestCallable {

    public static void main(String[] args) {

        ConCurrentCaculate cal = new ConCurrentCaculate();
        FutureTask<Integer> calTask = new FutureTask<Integer>(cal);
        
        new Thread(calTask).start();

        try {
            Integer sum = calTask.get();
            System.out.println(sum);
            System.out.println("------------------------------------");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        
    }

}
