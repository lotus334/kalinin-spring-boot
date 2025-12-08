package com.example;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExceed {

    public static void main(String[] args) {
        // Предположим, у нас executor с фиксированным числом
        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {

            // Опасный паттерн:
            executor.submit(createCallable());
            executor.submit(createCallable());
            executor.submit(createCallable());
        }
    }

    private static Callable<String> createCallable() {
        return () -> {
            System.out.println("Starting task");
            CompletableFuture<Void> future = new CompletableFuture<>();

            // Эта операция будет выполнена тем же потоком из пула,
            // который вызовет future.complete()
            future.thenRun(() -> {
                System.out.println("Then run");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Then run done");
            });

            System.out.println("complete");
            // Завершаем future в том же потоке
            future.complete(null);

            // Этот код может никогда не выполниться, если все потоки
            // окажутся заблокированы в thenRun!
            return "Done";
        };
    }
}
