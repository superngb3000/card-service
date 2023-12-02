package com.superngb.cardservice.domain;

import com.superngb.cardservice.model.CardDtoModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//TODO переделать обертку
public interface CardOutputBoundary {
    CardDtoModel prepareSuccessPostCardView(CardDtoModel model);
    CardDtoModel prepareFailPostCardView();
    CardDtoModel prepareSuccessGetCardView(CardDtoModel model);
    CardDtoModel prepareFailGetCardView();
    CardDtoModel prepareSuccessUpdateCardView(CardDtoModel model);
    CardDtoModel prepareFailUpdateCardView();
    CardDtoModel prepareSuccessDeleteCardView(CardDtoModel model);
    CardDtoModel prepareFailDeleteCardView();
    List<CardDtoModel> convertCard(List<CardDtoModel> modelList);
}
