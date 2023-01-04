package com.javathinked.application.numerology.controller.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PersonDto {

    private Long id;
    private String firstName;
    private String lastName;
    private int day;
    private int month;
    private int year;
    private String phoneNumber;
    private String email;
    private String language;
}
