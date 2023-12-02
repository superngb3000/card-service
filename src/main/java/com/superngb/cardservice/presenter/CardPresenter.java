package com.superngb.cardservice.presenter;

import com.superngb.cardservice.domain.CardOutputBoundary;
import com.superngb.cardservice.model.CardDtoModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardPresenter implements CardOutputBoundary {
    @Override
    public CardDtoModel prepareSuccessPostCardView(CardDtoModel model) {
        return model;
    }

    @Override
    public CardDtoModel prepareFailPostCardView() {
        return null;
    }

    @Override
    public CardDtoModel prepareSuccessGetCardView(CardDtoModel model) {
        return model;
    }

    @Override
    public CardDtoModel prepareFailGetCardView() {
        return null;
    }

    @Override
    public CardDtoModel prepareSuccessUpdateCardView(CardDtoModel model) {
        return model;
    }

    @Override
    public CardDtoModel prepareFailUpdateCardView() {
        return null;
    }

    @Override
    public CardDtoModel prepareSuccessDeleteCardView(CardDtoModel model) {
        return model;
    }

    @Override
    public CardDtoModel prepareFailDeleteCardView() {
        return null;
    }

    @Override
    public List<CardDtoModel> convertCard(List<CardDtoModel> modelList) {
        return modelList;
    }
}
