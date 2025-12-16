package com.bootRestGzip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<com.bootRestGzip.Item, Long> {
}