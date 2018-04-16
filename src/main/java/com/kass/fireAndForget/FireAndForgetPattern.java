package com.kass.fireAndForget;

import java.util.Random;

public class FireAndForgetPattern {

    public static void main(String[] args) {
        String curThr = Thread.currentThread().getName();
        try {
            // do some work in a none blocking way
            doSomeWorkInOwnThread();

            // execution continues here regardless of what is happening in Ali's tread below
            for (int i = 0; i < 10; i++) {
                System.out.println("in thread " + curThr + " continuing my work");
                Thread.sleep(1000);
            }
            System.out.println("\nin thread " + curThr + " work done.");
        } catch (Exception e) {e.printStackTrace();}
    }

    private static void doSomeWorkInOwnThread() {
        // do some work in a none blocking way
        new Thread("Ali's tread") {
            public void run() {
                // fire and forget
                // Ali: add you code here, this is the method that does the work asynchronously
                try {
                    int sleep = new Random().nextInt(5);
                    Thread.sleep(sleep * 1000);
                    System.out.println("\n"+Thread.currentThread().getName() + " took " + sleep + " sec. to do his work. done." +"\n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
