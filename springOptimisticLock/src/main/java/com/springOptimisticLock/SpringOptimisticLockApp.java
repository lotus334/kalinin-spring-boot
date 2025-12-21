package com.springOptimisticLock;

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
public class SpringOptimisticLockApp implements CommandLineRunner {

    @Autowired
    private final SeatService seatService;

    public static void main(String[] args) {
        SpringApplication.run(SpringOptimisticLockApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        seatService.createSeat();

        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {
            Callable<Boolean> callable = () -> {
                Seat seat = seatService.findAll().stream().findFirst().orElseThrow();
                Long id = seat.getId();
                return seatService.reserveSeat(id);
            };
            List<Callable<Boolean>> callables = List.of(callable, callable);
            List<Future<Boolean>> futures = executorService.invokeAll(callables);

            for (Future<Boolean> future : futures) {
                System.out.println("Поток забронировал место: " + future.get(5, java.util.concurrent.TimeUnit.SECONDS));
            }
        }
    }
}