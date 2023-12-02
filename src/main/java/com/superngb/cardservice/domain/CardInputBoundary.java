package com.superngb.cardservice.domain;

import com.superngb.cardservice.model.CardDtoModel;
import com.superngb.cardservice.model.CardPostModel;
import com.superngb.cardservice.model.CardUpdateModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CardInputBoundary {
    CardDtoModel createCard(CardPostModel cardPostModel);
    CardDtoModel getCard(Long id);
    List<CardDtoModel> getCards();
    List<CardDtoModel> getCardsByBoard(Long id);
    CardDtoModel updateCard(CardUpdateModel cardUpdateModel);
    CardDtoModel deleteCard(Long id);
}
