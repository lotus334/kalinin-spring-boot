package com.springOptimisticPessimisticLock;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepositorySpringDataJPA extends JpaRepository<Seat, Long> {

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE) // TODO почему-то не работает, может быть нужен @Query
    Optional<Seat> findById(Long id);
}
