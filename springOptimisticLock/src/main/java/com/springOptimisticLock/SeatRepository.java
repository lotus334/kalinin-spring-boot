package com.springOptimisticLock;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class SeatRepository {

    // Семантика JPA. Разницы не должно быть
    @PersistenceContext
    private final EntityManager entityManagerJPA;

    // Семантика Spring. Разницы не должно быть
    @Autowired
    private final EntityManager entityManagerSpring;

    public Seat findByIdJPA(Long id) {
        return entityManagerJPA.find(Seat.class, id, LockModeType.OPTIMISTIC);
    }

    public List<Seat> findAll() {
        return entityManagerJPA.createQuery("select s from Seat s", Seat.class).getResultList();
    }

    public Seat findByIdSpring(Long id) {
        Seat seat = entityManagerSpring.find(Seat.class, id);
        entityManagerSpring.lock(seat, LockModeType.OPTIMISTIC);
        return seat;
    }

    public void saveSpring(Seat seat) {
        entityManagerSpring.merge(seat);
    }
}
