package com.example;

import java.util.concurrent.CompletableFuture;

public class ThreadExample {

    public static void main(String[] args) throws InterruptedException {

        // выполнится в ForkJoinPool.commonPool
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> wait(0, "runAsync"));

        // выполнится в ForkJoinPool.commonPool
        future.thenAccept(integer -> wait(0, "thenAccept"));

        // Ожидание завершения future
        Thread.sleep(1000);

        // поток 1
        new Thread(() -> future.complete(null), "1").start();

        // Следующий пример
        Thread.sleep(1000);
        System.out.println();

        // начтён выполняться в ForkJoinPool.commonPool, но не завершится
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> wait(50, "runAsync"));

        // выполнится в потоке 3
        future2.thenAccept(integer -> wait(0, "thenAccept"));

        // поток 3
        new Thread(() -> future2.complete(null), "3").start();
    }

    private static void wait(int seconds, String actionName) {
        System.out.println(actionName + " in " + Thread.currentThread().getName());
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
