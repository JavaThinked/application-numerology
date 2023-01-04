package com.javathinked.application.numerology.service.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Person {

    private Long id;
    @NotEmpty(message = "The First Name is mandatory")
    private String firstName;
    @NotEmpty(message = "The Last Name is mandatory")
    private String lastName;
    private int day;
    private int month;
    private int year;
    @NotEmpty(message = "The Phone Number is mandatory")
    private String phoneNumber;
    @Email(message = "The Email is mandatory")
    private String email;
    @NotEmpty(message = "The Language is mandatory")
    private String language;
}
