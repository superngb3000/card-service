package com.superngb.cardservice.model;

import com.superngb.cardservice.entity.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardDtoModel {
    private Long id;
    private String name;
    private Long boardId;
    private Long creatorId;

    public static CardDtoModel mapper(Card card) {
        return new CardDtoModel(
                card.getId(),
                card.getName(),
                card.getBoardId(),
                card.getCreatorId());
    }

    public static List<CardDtoModel> mapper(List<Card> cardList) {
        return cardList.stream()
                .map(CardDtoModel::mapper)
                .toList();
    }
}
