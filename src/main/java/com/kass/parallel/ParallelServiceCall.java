package com.kass.parallel;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Code example on how ExecutorService, Future and callable can be used to make concurrent service calls
 * <p/>
 * Basically, java provides the concurrency package to do such a thing
 * <p/>
 * <p/>
 * Created by kass on 2017-01-05.
 */

public class ParallelServiceCall {
    private static final int POOL_SIZE = 5;
    private static final int SERVICE1_TIMEOUT = 5;
    private static final int SERVICE2_TIMEOUT = 5;
    private static final int RANDOM_NUM_MAX = 10;


    public static void main(String args[]) {

        //Fetching ExecutorService from Executors utility, thread pool size is 5
        ExecutorService executor = Executors.newFixedThreadPool(POOL_SIZE);
        //Create Callable instance and submit Callable tasks to be executed by the thread pool
        Future<StringBuffer> future1 = executor.submit(new Callable<StringBuffer>() {
            @Override
            public StringBuffer call() throws Exception {

                // simulates first service call. Tony, this is where the service call goes. This should return the object
                // that the service call returns
                int rn = ParallelServiceCall.getRandom("serviceCall1");
                Thread.sleep(rn * 1000);
                return new StringBuffer(Thread.currentThread().getName() + " serviceCall1 took " + rn);
            }
        });

        Future<String> future2 = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {

                int rn = ParallelServiceCall.getRandom("serviceCall2");
                Thread.sleep(rn * 1000);
                return Thread.currentThread().getName() + " serviceCall2 took " + rn;
            }
        });

        try {
            // future1.get return value of the Future. the 'get' method is blocking and waits for
            // the task to complete or it times out after the specified timeout
            System.out.println(future1.get(SERVICE1_TIMEOUT, TimeUnit.SECONDS));
        } catch (Exception e) {
            //handel any exception like timeout
            e.printStackTrace();
        }
        try {
            System.out.println(future2.get(SERVICE2_TIMEOUT, TimeUnit.SECONDS));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //shut down the executor service
        executor.shutdown();
    }

    // util method that gets a random num between 0 and RANDOM_NUM_MAX
    static public int getRandom(String service) {
        Random r = new Random();

        int rn = r.nextInt(RANDOM_NUM_MAX);
        System.out.println(service + " random num is: " + rn);
        return rn;
    }
}