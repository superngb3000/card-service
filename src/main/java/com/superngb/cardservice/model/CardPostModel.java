package com.superngb.cardservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardPostModel {
    @NotBlank
    private String name;
    @NotNull
    private Long boardId;
    @NotNull
    private Long creatorId;
}
