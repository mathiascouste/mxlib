package test.async;

import eu.couste.common.async.Async;
import eu.couste.common.async.Task;


public class Test {

    public static void main(String[] args) {
        // Test sans async
        long start = System.currentTimeMillis();

        sleeep();
        sleeep();
        sleeep();
        sleeep();
        sleeep();
        sleeep();
        sleeep();
        sleeep();
        sleeep();
        sleeep();

        System.out.println("NO ASYNC : exec time : " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();

        Async.run(new Task("1") {

            @Override
            public Object run() {
                sleeep();
                return null;
            }
        }, new Task("2") {

            @Override
            public Object run() {
                sleeep();
                return null;
            }
        }, new Task("3") {

            @Override
            public Object run() {
                sleeep();
                return null;
            }
        }, new Task("4") {

            @Override
            public Object run() {
                sleeep();
                return null;
            }
        }, new Task("5") {

            @Override
            public Object run() {
                sleeep();
                return null;
            }
        }, new Task("6") {

            @Override
            public Object run() {
                sleeep();
                return null;
            }
        }, new Task("7") {

            @Override
            public Object run() {
                sleeep();
                return null;
            }
        }, new Task("8") {

            @Override
            public Object run() {
                sleeep();
                return null;
            }
        }, new Task("9") {

            @Override
            public Object run() {
                sleeep();
                return null;
            }
        }, new Task("10") {

            @Override
            public Object run() {
                sleeep();
                return null;
            }
        }).joinAll();

        System.out.println("WITH ASYNC :exec time : " + (System.currentTimeMillis() - start));
    }

    public static void sleeep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
