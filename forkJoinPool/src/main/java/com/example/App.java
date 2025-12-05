package com.example;

import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class App {
    public static void main( String[] args ) {
        int availableProcessors = Runtime.getRuntime().availableProcessors();

        try (ForkJoinPool forkJoinPool = new ForkJoinPool(availableProcessors)) {
            long start = System.currentTimeMillis();
            ForkJoinTask<BigInteger> submit = forkJoinPool.submit(new FibonacciTwoJoin(30));
            BigInteger result = submit.join();
            System.out.println(result);
            System.out.println(System.currentTimeMillis() - start);
        }
    }

    /**
     * Кажется не распараллеливает толком. Медленнее того что ниже
     */
    @RequiredArgsConstructor
    private static class Fibonacci extends RecursiveTask<BigInteger> {

        private final int number;

        @Override
        protected BigInteger compute() {
            if (number <= 1) {
                return BigInteger.valueOf(number);
            }
            Fibonacci fibonacciTwoJoin1 = new Fibonacci(number - 1);
            fibonacciTwoJoin1.fork();
            return fibonacciTwoJoin1.compute().add(new Fibonacci(number - 2).join());
        }
    }

    @RequiredArgsConstructor
    private static class FibonacciTwoJoin extends RecursiveTask<BigInteger> {

        private final int number;

        @Override
        protected BigInteger compute() {
            if (number <= 1) {
                return BigInteger.valueOf(number);
            }
            FibonacciTwoJoin fibonacciTwoJoin1 = new FibonacciTwoJoin(number - 1);
            fibonacciTwoJoin1.fork();
            FibonacciTwoJoin fibonacciTwoJoin2 = new FibonacciTwoJoin(number - 2);
            fibonacciTwoJoin2.fork();
            return fibonacciTwoJoin1.join().add(fibonacciTwoJoin2.join());
        }
    }
}
