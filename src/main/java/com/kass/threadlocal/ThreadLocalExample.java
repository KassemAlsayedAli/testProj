package com.kass.threadlocal;

import java.util.Random;

/**
 * Created by Kassem A. Ali on 2018-03-02.
 */
public class ThreadLocalExample extends Thread {

    private static final ThreadLocal<String> context = new ThreadLocal<String>() {
        @Override protected String initialValue() {
            return "This is the initial value of context - if you see this that means it is not set";
        }
    };


    public void setContext() {
        context.set(Thread.currentThread().getName() +": " + ((Integer) new Random().nextInt(100)));
    }

    public String getContext() {
        return context.get();
    }

    @Override
    public void run() {
        setContext();
        try {
            Thread.sleep(1000);
            System.out.println(context.get());
        } catch (InterruptedException e) {
        }

    }

    public static void main(String[] args) {
        try {
            ThreadLocalExample t1 = new ThreadLocalExample();
            ThreadLocalExample t2 = new ThreadLocalExample();

            t1.start();
            t2.start();

            //System.out.println("t1 context: " + t1.getContext());
            //System.out.println("t2 context: " + t2.getContext());

            t1.join(); //wait for thread 1 to terminate
            t2.join(); //wait for thread 2 to terminate

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

