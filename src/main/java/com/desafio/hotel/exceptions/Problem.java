package com.desafio.hotel.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class Problem {
    private String title;
    private int status;
    private String message;
}