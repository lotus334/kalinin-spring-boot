package com.example;

import java.util.concurrent.CompletableFuture;

public class OrderAsync {

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();
//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");

        // Добавляем зависимые операции
        future.thenApply(s -> {
            System.out.println("thenApply: " + Thread.currentThread().getName());
            return s + " transformed";
        });

        future.thenApplyAsync(s -> {
            System.out.println("thenApplyAsync: " + Thread.currentThread().getName());
            return s + " transformed async";
        });

        Thread.sleep(1000);

        // Завершаем фьючу в отдельном потоке
        new Thread(() -> {
            System.out.println("completing in: " + Thread.currentThread().getName());
            future.complete("Result"); // TODO без complete не завершится. join не завершит, так как первая future не задана
//            future.join();
        }).start();
//        future.join();

        // completing in: Thread-0
        // thenApply: Thread-0
        // thenApplyAsync: ForkJoinPool.commonPool-worker-1
    }
}
