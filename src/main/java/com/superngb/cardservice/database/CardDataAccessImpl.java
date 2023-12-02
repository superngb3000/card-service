package com.superngb.cardservice.database;

import com.superngb.cardservice.domain.CardDataAccess;
import com.superngb.cardservice.entity.Card;
import com.superngb.cardservice.repository.CardRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CardDataAccessImpl implements CardDataAccess {

    private final CardRepository cardRepository;

    public CardDataAccessImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Card save(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card findById(Long id) {
        return cardRepository.findById(id).orElse(null);
    }

    @Override
    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    @Override
    public List<Card> findCardsByBoardId(Long id) {
        return cardRepository.findAllByBoardId(id).orElse(null);
    }

    @Override
    public Card deleteById(Long id) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (optionalCard.isPresent()) {
            cardRepository.deleteById(id);
            return optionalCard.get();
        }
        return null;
    }
}
