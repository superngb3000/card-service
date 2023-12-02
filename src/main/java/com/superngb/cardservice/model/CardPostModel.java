package com.superngb.cardservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardPostModel {
    private String name;
    private Long boardId;
    private Long creatorId;
}
