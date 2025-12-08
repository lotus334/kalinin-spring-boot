package com.example;

import java.util.concurrent.CompletableFuture;

public class BestPractice {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> waitAndReturn("Hello"))
                .thenApplyAsync(s -> waitAndReturn(s + " World"));
        completableFuture.thenAccept(System.out::println);

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> waitAndReturn("!!!"))
                .thenApplyAsync(s -> waitAndReturn(s + " Hell yeah"));

        completableFuture
                .thenCombine(completableFuture2, (s1, s2) -> s1 + s2)
                .whenComplete((res, ex) -> {
                    if (ex != null) {
                        ex.printStackTrace();
                        return;
                    }
                    System.out.println(res);
                    System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
                });

        Thread.sleep(2000); // Иначе main завершится раньше
    }

    private static String waitAndReturn(String s) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return s;
    }
}
