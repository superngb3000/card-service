package com.superngb.cardservice.controller;

import com.superngb.cardservice.domain.CardInputBoundary;
import com.superngb.cardservice.model.CardDtoModel;
import com.superngb.cardservice.model.CardPostModel;
import com.superngb.cardservice.model.CardUpdateModel;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CardController {

    private final CardInputBoundary cardInputBoundary;

    public CardController(CardInputBoundary cardInputBoundary) {
        this.cardInputBoundary = cardInputBoundary;
    }

    @PostMapping
    public CardDtoModel postCard(@RequestBody @Valid CardPostModel model) {
        return cardInputBoundary.createCard(model);
    }

    @GetMapping("/{id}")
    public CardDtoModel getCard(@PathVariable Long id) {
        return cardInputBoundary.getCard(id);
    }

    @GetMapping
    public List<CardDtoModel> getCards() {
        return cardInputBoundary.getCards();
    }

    @GetMapping("board/{id}")
    public List<CardDtoModel> getCardsByBoard(@PathVariable Long id) {
        return cardInputBoundary.getCardsByBoard(id);
    }

    @PutMapping
    public CardDtoModel updateCard(@RequestBody @Valid CardUpdateModel model) {
        return cardInputBoundary.updateCard(model);
    }

    @DeleteMapping("/{id}")
    public CardDtoModel deleteCard(@PathVariable Long id) {
        return cardInputBoundary.deleteCard(id);
    }

    @DeleteMapping("/deleteByBoard/{id}")
    void deleteCardsByBoard(@PathVariable Long id){
        cardInputBoundary.deleteCardsByBoard(id);
    }

    @GetMapping("/cardExists/{id}")
    boolean cardExists(@PathVariable Long id){
        return cardInputBoundary.cardExists(id);
    }
}
