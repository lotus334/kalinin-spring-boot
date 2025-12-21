package com.springOptimisticLock;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {

    @Autowired
    private final SeatRepository repository;

    public void createSeat() {
        Seat seat = new Seat();
        repository.saveSpring(seat);
    }

    public List<Seat> findAll() {
        return repository.findAll();
    }

    public boolean reserveSeat(Long seatId) {
        Seat seat = repository.findByIdSpring(seatId);

        if (seat.isAvailable()) {
            // Занимаем место
            seat.setAvailable(false);

            // Попробуем сохранить изменения
            try {
                repository.saveSpring(seat);
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
}
