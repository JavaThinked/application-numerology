package com.javathinked.application.numerology.controller;

import lombok.Data;

@Data
public class Response {

    private final int statusValue;
    private final String statusReasonPhrase;
    private final String dateTime;
    private final String description;
    private final Object detail;
}
