package com.superngb.cardservice.domain;

import com.superngb.cardservice.entity.Card;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CardDataAccess {
    Card save(Card user);

    Card findById(Long id);

    List<Card> getCards();

    List<Card> findCardsByBoardId(Long id);

    Card deleteById(Long id);
}
