package com.superngb.cardservice.domain;

import com.superngb.cardservice.client.BoardServiceClient;
import com.superngb.cardservice.client.TaskServiceClient;
import com.superngb.cardservice.client.UserServiceClient;
import com.superngb.cardservice.entity.Card;
import com.superngb.cardservice.model.CardDtoModel;
import com.superngb.cardservice.model.CardPostModel;
import com.superngb.cardservice.model.CardUpdateModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
public class CardInteractor implements CardInputBoundary {

    private final CardDataAccess cardDataAccess;
    private final CardOutputBoundary cardOutputBoundary;
    private final UserServiceClient userServiceClient;
    private final BoardServiceClient boardServiceClient;
    private final TaskServiceClient taskServiceClient;

    public CardInteractor(CardDataAccess cardDataAccess,
                          CardOutputBoundary cardOutputBoundary,
                          UserServiceClient userServiceClient,
                          BoardServiceClient boardServiceClient,
                          TaskServiceClient taskServiceClient) {
        this.cardDataAccess = cardDataAccess;
        this.cardOutputBoundary = cardOutputBoundary;
        this.userServiceClient = userServiceClient;
        this.boardServiceClient = boardServiceClient;
        this.taskServiceClient = taskServiceClient;
    }

    @Override
    public CardDtoModel createCard(CardPostModel cardPostModel) {
        if (!userServiceClient.userExists(cardPostModel.getCreatorId())) {
            return cardOutputBoundary.prepareFailPostCardView();
        }
        if (!boardServiceClient.boardExists(cardPostModel.getBoardId())) {
            return cardOutputBoundary.prepareFailPostCardView();
        }
        return cardOutputBoundary.prepareSuccessPostCardView(CardDtoModel.mapper(
                cardDataAccess.save(Card.builder()
                        .name(cardPostModel.getName())
                        .boardId(cardPostModel.getBoardId())
                        .creatorId(cardPostModel.getCreatorId())
                        .build())));
    }


    @Override
    public CardDtoModel getCard(Long id) {
        Card card = cardDataAccess.findById(id);
        return (card == null)
                ? cardOutputBoundary.prepareFailGetCardView()
                : cardOutputBoundary.prepareSuccessGetCardView(CardDtoModel.mapper(card));
    }

    @Override
    public List<CardDtoModel> getCards() {
        return cardOutputBoundary.convertCard(CardDtoModel.mapper(cardDataAccess.getCards()));
    }

    @Override
    public List<CardDtoModel> getCardsByBoard(Long id) {
        return cardOutputBoundary.convertCard(CardDtoModel.mapper(cardDataAccess.findCardsByBoardId(id)));
    }

    @Override
    public CardDtoModel updateCard(CardUpdateModel cardUpdateModel) {
        Card cardById = cardDataAccess.findById(cardUpdateModel.getId());
        if (cardById == null){
            return cardOutputBoundary.prepareFailUpdateCardView();
        }
        updateFieldIfNotNull(cardUpdateModel.getName(), cardById::getName, cardById::setName);
        return cardOutputBoundary.prepareSuccessUpdateCardView(CardDtoModel.mapper(cardDataAccess.save(cardById)));
    }

    private <T> void updateFieldIfNotNull(T newValue, Supplier<T> currentValueSupplier, Consumer<T> updateFunction) {
        T currentValue = currentValueSupplier.get();
        if (newValue != null && (currentValue == null || !Objects.equals(currentValue, newValue))) {
            updateFunction.accept(newValue);
        }
    }

    @Override
    public CardDtoModel deleteCard(Long id) {
        Card card = cardDataAccess.deleteById(id);
        if (card == null){
            return cardOutputBoundary.prepareFailDeleteCardView();
        }
        taskServiceClient.deleteTasksByCard(id);
        return cardOutputBoundary.prepareSuccessDeleteCardView(CardDtoModel.mapper(card));
    }

    @Override
    public void deleteCardsByBoard(Long id) {
        cardDataAccess.findCardsByBoardId(id).forEach(card -> cardDataAccess.deleteById(card.getId()));
    }

    @Override
    public boolean cardExists(Long id) {
        return (cardDataAccess.findById(id) != null)
                ? cardOutputBoundary.prepareCardExistsView()
                : cardOutputBoundary.prepareCardDoesNotExistView();
    }
}
