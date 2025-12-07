package com.example;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class MainExamples {

    public static void main( String[] args ) {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(5);
//            number = 4;

            if (number == 0) {
                System.out.println(number);
                return 0;

            } else if (number == 1) {
                System.out.println(number + " throws");
                throw new RuntimeException("Inner error");

            } else if (number == 2) {
                try {
                    System.out.println(number + " sleeps");
                    Thread.sleep(2000);
                    System.out.println(number + " ends");
                    return number;
                } catch (InterruptedException e) {
                    throw new RuntimeException("Sleep error");
                }

            } else if (number == 3) {
                System.out.println(number + " interrupts");
                Thread.currentThread().interrupt();
                return number;

            } else {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return number;
            }
        });

//        System.out.println("join = " + completableFuture.join()); // Прокидывает исключение

//        System.out.println("complete = " + completableFuture.complete(9)); // Завершает Future

//        System.out.println("completeExceptionally = " + completableFuture.completeExceptionally(new RuntimeException("Outer error"))); // Завершает Future с ошибкой

//        System.out.println("cancel = " + completableFuture.cancel(true)); // бросает CancellationException

        // Для 3
//        try {
//            System.out.println("future result = " + completableFuture.get()); // TODO Interruptible не понял как работает
//        } catch (InterruptedException | ExecutionException e) {
//            throw new RuntimeException(e);
//        }

        // Для 4
        try {
            System.out.println("on timeout = " + completableFuture.completeOnTimeout(10, 3, TimeUnit.SECONDS).get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
