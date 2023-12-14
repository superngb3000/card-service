package com.superngb.cardservice.domain;

import com.superngb.cardservice.model.CardPostModel;
import com.superngb.cardservice.model.CardUpdateModel;
import com.superngb.cardservice.model.ResponseModel;
import org.springframework.stereotype.Component;

@Component
public interface CardInputBoundary {
    ResponseModel<?> createCard(CardPostModel cardPostModel);

    ResponseModel<?> getCard(Long id);

    ResponseModel<?> getCards();

    ResponseModel<?> getCardsByBoard(Long id);

    ResponseModel<?> updateCard(CardUpdateModel cardUpdateModel);

    ResponseModel<?> deleteCard(Long id);

    ResponseModel<?> deleteCardsByBoard(Long id);
}
