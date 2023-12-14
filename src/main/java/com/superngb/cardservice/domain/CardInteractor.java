package com.superngb.cardservice.domain;

import com.superngb.cardservice.client.BoardServiceClient;
import com.superngb.cardservice.client.TaskServiceClient;
import com.superngb.cardservice.client.UserServiceClient;
import com.superngb.cardservice.entity.Card;
import com.superngb.cardservice.model.CardDtoModel;
import com.superngb.cardservice.model.CardPostModel;
import com.superngb.cardservice.model.CardUpdateModel;
import com.superngb.cardservice.model.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
public class CardInteractor implements CardInputBoundary {

    private final CardDataAccess cardDataAccess;
    private final UserServiceClient userServiceClient;
    private final BoardServiceClient boardServiceClient;
    private final TaskServiceClient taskServiceClient;

    public CardInteractor(CardDataAccess cardDataAccess,
                          UserServiceClient userServiceClient,
                          BoardServiceClient boardServiceClient,
                          TaskServiceClient taskServiceClient) {
        this.cardDataAccess = cardDataAccess;
        this.userServiceClient = userServiceClient;
        this.boardServiceClient = boardServiceClient;
        this.taskServiceClient = taskServiceClient;
    }

    @Override
    public ResponseModel<?> createCard(CardPostModel cardPostModel) {
        ResponseEntity<?> getCreatorResponse = userServiceClient.getUser(cardPostModel.getCreatorId());
        if (!getCreatorResponse.getStatusCode().equals(HttpStatus.valueOf(200))) {
            return ResponseModel.builder().code(403).body("User with userId = " + cardPostModel.getCreatorId().toString() + " does not exist").build();
        }
        ResponseEntity<?> getBoardResponse = boardServiceClient.getBoard(cardPostModel.getBoardId());
        if (!getBoardResponse.getStatusCode().equals(HttpStatus.valueOf(200))) {
            return ResponseModel.builder().code(403).body("Board with boardId = " + cardPostModel.getBoardId().toString() + " does not exist").build();
        }
        return ResponseModel.builder().code(200).body(
                CardDtoModel.mapper(
                        cardDataAccess.save(Card.builder()
                                .name(cardPostModel.getName())
                                .boardId(cardPostModel.getBoardId())
                                .creatorId(cardPostModel.getCreatorId())
                                .build())
                )
        ).build();
    }

    @Override
    public ResponseModel<?> getCard(Long id) {
        Card card = cardDataAccess.findById(id);
        return (card == null)
                ? ResponseModel.builder().code(404).body("Card with cardId = " + id.toString() + " not found").build()
                : ResponseModel.builder().code(200).body(CardDtoModel.mapper(card)).build();
    }

    @Override
    public ResponseModel<?> getCards() {
        return ResponseModel.builder().code(200).body(CardDtoModel.mapper(cardDataAccess.getCards())).build();
    }

    @Override
    public ResponseModel<?> getCardsByBoard(Long id) {
        List<Card> cardList = cardDataAccess.findCardsByBoardId(id);
        return (cardList == null)
                ? ResponseModel.builder().code(404).body("There are no cards with board with boardId = " + id.toString()).build()
                : ResponseModel.builder().code(200).body(CardDtoModel.mapper(cardList)).build();
    }

    @Override
    public ResponseModel<?> updateCard(CardUpdateModel cardUpdateModel) {
        Card cardById = cardDataAccess.findById(cardUpdateModel.getId());
        if (cardById == null) {
            return ResponseModel.builder().code(404).body("Card with cardId = " + cardUpdateModel.getId().toString() + " not found").build();
        }
        updateFieldIfNotNull(cardUpdateModel.getName(), cardById::getName, cardById::setName);
        return ResponseModel.builder().code(200).body(CardDtoModel.mapper(cardDataAccess.save(cardById))).build();
    }

    private <T> void updateFieldIfNotNull(T newValue, Supplier<T> currentValueSupplier, Consumer<T> updateFunction) {
        T currentValue = currentValueSupplier.get();
        if (newValue != null && (currentValue == null || !Objects.equals(currentValue, newValue))) {
            updateFunction.accept(newValue);
        }
    }

    @Override
    public ResponseModel<?> deleteCard(Long id) {
        Card card = cardDataAccess.deleteById(id);
        if (card == null) {
            return ResponseModel.builder().code(404).body("Card with cardId = " + id.toString() + " not found").build();
        }
        taskServiceClient.deleteTasksByCard(id);
        return ResponseModel.builder().code(200).body(CardDtoModel.mapper(card)).build();
    }

    @Override
    public ResponseModel<?> deleteCardsByBoard(Long id) {
        cardDataAccess.findCardsByBoardId(id).forEach(card -> cardDataAccess.deleteById(card.getId()));
        List<Card> cardList = cardDataAccess.findCardsByBoardId(id);
        return (cardList == null)
                ? ResponseModel.builder().code(403).body("There are no cards with board with boardId = " + id.toString()).build()
                : ResponseModel.builder().code(200).body(CardDtoModel.mapper(cardList)).build();
    }
}
