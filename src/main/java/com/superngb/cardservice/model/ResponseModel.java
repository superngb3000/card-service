package com.superngb.cardservice.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel<T> {
    private Integer code;
    private T body;
}