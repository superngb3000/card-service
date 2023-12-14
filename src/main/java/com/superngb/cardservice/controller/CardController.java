package com.superngb.cardservice.controller;

import com.superngb.cardservice.domain.CardInputBoundary;
import com.superngb.cardservice.model.CardPostModel;
import com.superngb.cardservice.model.CardUpdateModel;
import com.superngb.cardservice.model.ResponseModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class CardController {

    private final CardInputBoundary cardInputBoundary;

    public CardController(CardInputBoundary cardInputBoundary) {
        this.cardInputBoundary = cardInputBoundary;
    }

    @PostMapping
    public ResponseEntity<?> postCard(@RequestBody @Valid CardPostModel model) {
        ResponseModel<?> response = cardInputBoundary.createCard(model);
        return new ResponseEntity<>(response.getBody(), HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCard(@PathVariable Long id) {
        ResponseModel<?> response = cardInputBoundary.getCard(id);
        return new ResponseEntity<>(response.getBody(), HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping
    public ResponseEntity<?> getCards() {
        ResponseModel<?> response = cardInputBoundary.getCards();
        return new ResponseEntity<>(response.getBody(), HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("board/{id}")
    public ResponseEntity<?> getCardsByBoard(@PathVariable Long id) {
        ResponseModel<?> response = cardInputBoundary.getCardsByBoard(id);
        return new ResponseEntity<>(response.getBody(), HttpStatus.valueOf(response.getCode()));
    }

    @PutMapping
    public ResponseEntity<?> updateCard(@RequestBody @Valid CardUpdateModel model) {
        ResponseModel<?> response = cardInputBoundary.updateCard(model);
        return new ResponseEntity<>(response.getBody(), HttpStatus.valueOf(response.getCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable Long id) {
        ResponseModel<?> response = cardInputBoundary.deleteCard(id);
        return new ResponseEntity<>(response.getBody(), HttpStatus.valueOf(response.getCode()));
    }

    @DeleteMapping("/deleteByBoard/{id}")
    public ResponseEntity<?> deleteCardsByBoard(@PathVariable Long id) {
        ResponseModel<?> response = cardInputBoundary.deleteCardsByBoard(id);
        return new ResponseEntity<>(response.getBody(), HttpStatus.valueOf(response.getCode()));
    }
}
