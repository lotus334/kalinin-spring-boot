package com.example;

import java.util.concurrent.CompletableFuture;

public class Order {

    public static void main(String[] args) {
        CompletableFuture<String> future = new CompletableFuture<>();

        // Создаём цепочку: future -> a -> b -> c
        CompletableFuture<String> a = future.thenApply(s -> {
            System.out.println("Операция A");
            return s + " + A";
        });

        CompletableFuture<String> b = a.thenApply(s -> {
            System.out.println("Операция B");
            return s + " + B";
        });

        CompletableFuture<String> c = b.thenApply(s -> {
            System.out.println("Операция C");
            return s + " + C";
        });

        // Добавляем ещё одну независимую операцию к исходной фьюче
        future.thenApply(s -> {
            System.out.println("Операция D");
            return s + " + D";
        });

        future.complete("Результат");

        // Вывод будет:
        // Операция D
        // Операция A
        // Операция B
        // Операция C
    }
}
