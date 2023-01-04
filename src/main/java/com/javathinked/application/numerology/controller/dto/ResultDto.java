package com.javathinked.application.numerology.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ResultDto {

    @JsonIgnore
    private Long id;
    private String category;
    private int number;
    private String message;
    private String language;
}
