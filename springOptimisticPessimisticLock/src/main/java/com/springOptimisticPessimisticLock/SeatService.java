package com.springOptimisticPessimisticLock;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeatService {

    @Autowired
    private final SeatRepositorySpringDataJPA defaultRepository;

    @Autowired
    private final SeatRepositoryEntityManager customRepository;

    public void createSeat() {
        defaultRepository.save(new Seat());
    }

    public Iterable<Seat> findAll() {
        return defaultRepository.findAll();
    }

    public boolean reserveSeatOptimistic(Long seatId) {
        Optional<Seat> seat = customRepository.findByIdJPAOptimistic(seatId);

        if (seat.isPresent() && seat.get().isAvailable()) {
            // Занимаем место
            seat.get().setAvailable(false);

            // Попробуем сохранить изменения
            try {
                defaultRepository.save(seat.get()); // TODO почему-то работает именно с save, а не flush
                return true;
            } catch (Exception e) {
                // Обработка исключения
                System.out.println(e.getClass());
                System.out.println("Ошибка: Место уже забронировано другим пользователем. Пожалуйста, попробуйте снова.");
                return false;
            }
        } else {
            System.out.println("Ошибка: Место уже недоступно.");
            return false;
        }
    }

    /**
     * Если не объявить @Transactional, то блокировка будет снята после первого обращения к БД и оба потока увидят что место свободно
     */
    @Transactional
    public boolean reserveSeatPessimistic(Long seatId) {
        Optional<Seat> seat = customRepository.findByIdSpringPessimistic(seatId);

        if (seat.isPresent() && seat.get().isAvailable()) {
            // Занимаем место
            seat.get().setAvailable(false);

            // Попробуем сохранить изменения
            return true;
        } else {
            System.out.println("Ошибка: Место уже недоступно.");
            return false;
        }
    }

    @Transactional
    public void releaseAll() {
        defaultRepository.findAll().forEach(seat -> {
            seat.setAvailable(true);
            defaultRepository.save(seat);
        });
    }
}
