package com.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolOutOfMemory {

    public static void main(String[] args) {

        // Потенциально опасный код
        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {
            // Если этот цикл выполняется быстрее, чем обрабатываются задачи,
            // очередь будет расти бесконечно
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    try {
                        Thread.sleep(1000); // Длительная операция
                        System.out.println("Task " + taskId + " complete");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
        }

        // Решение
        // int nThreads = 2;
        // int queueCapacity = 100;
        // ExecutorService executor = new ThreadPoolExecutor(
        //     nThreads, nThreads,
        //     0L, TimeUnit.MILLISECONDS,
        //     new LinkedBlockingQueue<>(queueCapacity)
        // );
    }
}
