package com.example;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedThreadPoolDeadLock {

    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger();

        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {

            System.out.println("start");
            Future<Integer> submit = executorService.submit(() -> {
                System.out.println("submit task 1");
                while (i.get() == 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                i.set(1);
                System.out.println("end task1");
            }, 0);

            executorService.submit(() -> {
                System.out.println("submit task 2");
                try {
                    i.set(submit.get());
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("end task2");
            });
        }

        System.out.println("done");
    }
}
