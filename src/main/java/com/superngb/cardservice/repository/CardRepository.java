package com.superngb.cardservice.repository;

import com.superngb.cardservice.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<List<Card>> findAllByBoardId(Long id);
}
