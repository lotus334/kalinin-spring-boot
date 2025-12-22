package com.springOptimisticPessimisticLock;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringOptimisticPessimisticLockApp implements CommandLineRunner {

    @Autowired
    private final SeatService seatService;

    public static void main(String[] args) {
        SpringApplication.run(SpringOptimisticPessimisticLockApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        seatService.createSeat();

        Iterable<Seat> all = seatService.findAll();
        System.out.println(all);
        Seat seat = all.iterator().next();
        Long id = seat.getId();

        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {

            System.out.println("Оптимистическая блокировка");

            Callable<Boolean> callable = () -> seatService.reserveSeatOptimistic(id);
            List<Callable<Boolean>> callables = List.of(callable, callable);
            List<Future<Boolean>> futures = executorService.invokeAll(callables);

            for (Future<Boolean> future : futures) {
                System.out.println("Поток забронировал место: " + future.get(5, java.util.concurrent.TimeUnit.SECONDS));
            }

            seatService.releaseAll();

            System.out.println("Пессимистическая блокировка");

            callable = () -> seatService.reserveSeatPessimistic(id);
            callables = List.of(callable, callable);
            futures = executorService.invokeAll(callables);

            for (Future<Boolean> future : futures) {
                System.out.println("Поток забронировал место: " + future.get(5, java.util.concurrent.TimeUnit.SECONDS));
            }

        } catch (Exception e) {
            System.out.println("Тут не должно быть исключений: " + e.getMessage());
        }

        System.out.println(seatService.findAll());
    }
}