package com.example;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

public class MainExamples {

    public static void main(String[] args) {

        CompletableFuture<Integer> source = getCompletableFuture(2);
        CompletableFuture<Integer> dependent = source.thenApply(multiplyBy(100));
        CompletableFuture<Integer> dependOnDependent = dependent.thenCompose(MainExamples::getCompletableFuture);
        System.out.println(dependOnDependent.join());

//        System.out.println("join = " + completableFuture.join()); // Прокидывает исключение

        // Явное завершение, не дожидаясь завершения Future.
//        CompletableFuture<Integer> completableFuture2 = getCompletableFuture(2);
//        System.out.println("complete = " + completableFuture2.complete(9)); // Завершает Future
//        System.out.println("completeExceptionally = " + completableFuture2
//                .completeExceptionally(new RuntimeException("Outer error"))); // Завершает Future с ошибкой
//        System.out.println("cancel = " + completableFuture2.cancel(true)); // бросает CancellationException
//        System.out.println(completableFuture2.join());

//        try {
//            System.out.println("future result = " + getCompletableFuture(3).get()); // TODO Interruptible не понял как работает
//        } catch (InterruptedException | ExecutionException e) {
//            System.out.println("catch exception = " + e.getMessage());
//        }

//        try {
//            System.out.println("on timeout = " + getCompletableFuture(4).completeOnTimeout(10, 3, TimeUnit.SECONDS).get());
//        } catch (InterruptedException | ExecutionException e) {
//            System.out.println("catch exception = " + e.getMessage());
//        }
    }

    private static CompletableFuture<Integer> getCompletableFuture(Integer outerNumber) {
        return CompletableFuture.supplyAsync(
                () -> {
                    int number = Objects.requireNonNullElseGet(outerNumber, () -> new Random().nextInt(5));

                    if (number == 0) {
                        System.out.println(number);
                        return 0;

                    } else if (number == 1) {
                        System.out.println(number + " throws");
                        throw new RuntimeException("Inner error");

                    } else if (number == 2) {
                        return sleepSecondsAndReturn(2, number);

                    } else if (number == 3) {
                        System.out.println(number + " interrupts");
                        Thread.currentThread().interrupt();
                        return number;

                    } else {
                        return sleepSecondsAndReturn(4, number);
                    }
                }
        );
    }

    private static int sleepSecondsAndReturn(int seconds, int number) {
        try {
            System.out.println(number + " sleeps");
            Thread.sleep(seconds * 1000L);
            System.out.println(number + " ends");
            return number;
        } catch (InterruptedException e) {
            throw new RuntimeException("Sleep error");
        }
    }

    private static Function<Integer, Integer> multiplyBy(int number) {
        return integer -> {
            System.out.println("inside thenApply");
            return integer * number;
        };
    }
}
