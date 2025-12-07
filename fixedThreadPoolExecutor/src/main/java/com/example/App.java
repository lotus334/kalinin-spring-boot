package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class App {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(3);
    private static final Map<String, Integer> map = new ConcurrentHashMap<>();
    private static final List<Integer> list = new ArrayList<>();
    private static final AtomicInteger counter = new AtomicInteger();

    public static void main(String[] args) {
        int numberOfTasks = 20;
        for (int i = 0; i < numberOfTasks; i++) {
            Runnable runnable = new Thread(createRunnable(), "Thread-" + i);
            executorService.submit(runnable);
        }

        executorService.shutdown();
        try {
            long start = System.currentTimeMillis();
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("Shutdown now");
                executorService.shutdownNow();
            }
            System.out.println("Finished in " + (System.currentTimeMillis() - start) + " ms");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(list.size());

        System.out.println("Количество задач = " + numberOfTasks + ". Количество запусков = " + counter.get());
    }

    private static Runnable createRunnable() {
        return () -> {
            counter.incrementAndGet();
            Integer numberOfThreadStarts = map.compute(Thread.currentThread().getName(), (k, v) -> (v == null) ? 1 : (v + 1));
            System.out.println("Thread " + Thread.currentThread().getName() + " starts with numberOfThreadStarts " + numberOfThreadStarts);
            for (int i = 0; i < 1000; i++) {
                for (int j = 0; j < 1000; j++) {
                    for (int k = 0; k < 20; k++) {
                        list.add(i + j + k);
                    }
                }
            }
        };
    }
}
