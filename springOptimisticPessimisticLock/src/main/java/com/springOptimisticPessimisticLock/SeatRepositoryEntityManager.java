package com.springOptimisticPessimisticLock;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SeatRepositoryEntityManager {

    // Семантика JPA. Разницы не должно быть
    @PersistenceContext
    private final EntityManager entityManagerJPA;

    // Семантика Spring. Разницы не должно быть
    @Autowired
    private final EntityManager entityManagerSpring;

    public Optional<Seat> findByIdJPAOptimistic(Long id) {
        return Optional.of(entityManagerJPA.find(Seat.class, id, LockModeType.OPTIMISTIC));
    }

    public Optional<Seat> findByIdSpringPessimistic(Long id) {
//        Seat seat = entityManagerSpring.find(Seat.class, id);
//        entityManagerSpring.lock(seat, LockModeType.PESSIMISTIC_WRITE); // TODO почему то не работает через метод lock
        return Optional.of(entityManagerJPA.find(Seat.class, id, LockModeType.PESSIMISTIC_WRITE));
    }
}
