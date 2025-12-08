package com.example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {

    public static void log() throws InterruptedException {
        String name = Thread.currentThread().getName(); //like Thread-1 or Thread-0
        int val = Integer.parseInt(Thread.currentThread().getName()) + 1;
        System.out.println(name + ": wait for " + val + " sec");
        Thread.sleep(val * 1000L);
    }

    public static void main(String[] args) {
        Lock first = new ReentrantLock();
        Lock second = new ReentrantLock();

        Runnable locker = () -> {
            boolean firstLocked = false;
            boolean secondLocked = false;
            try {
                while (!firstLocked || !secondLocked) {
                    firstLocked = first.tryLock(100, TimeUnit.MILLISECONDS);
                    log();
                    secondLocked = second.tryLock(100, TimeUnit.MILLISECONDS);
                    log();
                }
                first.unlock();
                second.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        // TODO livelock если thread-1 стартует раньше thread-0. Почему?
        new Thread(locker, "1").start();
        new Thread(locker, "0").start();
    }
}
