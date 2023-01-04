package com.javathinked.application.numerology.controller;

import com.javathinked.application.numerology.controller.dto.ResultDescriptionDto;
import com.javathinked.application.numerology.controller.exception.BadRequestException;
import com.javathinked.application.numerology.controller.exception.NotFoundException;
import com.javathinked.application.numerology.service.ResultDescriptionService;
import com.javathinked.application.numerology.service.exception.NumerologyCustomException;
import com.javathinked.application.numerology.service.exception.NumerologyResultNotFoundException;
import com.javathinked.application.numerology.util.MapperWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/numerology")
public class ResultDescriptionController {

    public static final String BAD_REQUEST_MESSAGE = "An error occurs while getting the description";
    public static final String NOT_FOUND_MESSAGE = "The result is not found for the description";
    public static final String DESCRIPTION_FOUND_MESSAGE = "Descriptions found for %s";
    private final ResultDescriptionService service;
    private final MapperWrapper mapper;

    @Autowired
    public ResultDescriptionController(ResultDescriptionService service, MapperWrapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/descriptions/{language}")
    public ResponseEntity<Response> findResultDescriptionsByLanguage(@PathVariable("language") String language) throws BadRequestException, NotFoundException {
        try {
            var descriptions = service.findResultDescriptionsByLanguage(language);
            var descriptionsDto = descriptions
                    .stream()
                    .map(resultDescription -> mapper.performMapping(resultDescription, ResultDescriptionDto.class))
                    .collect(Collectors.toList());
            var message = String.format(DESCRIPTION_FOUND_MESSAGE, language);
            var response = createResponseWithStatusOK(message, descriptionsDto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (NumerologyCustomException exception) {
            throw new BadRequestException(BAD_REQUEST_MESSAGE, exception);
        } catch (NumerologyResultNotFoundException exception) {
            throw new NotFoundException(NOT_FOUND_MESSAGE, exception);
        }
    }

    @GetMapping("/description/{category}/{language}")
    public ResponseEntity<Response> findResultDescriptionByCategoryAndLanguage(@PathVariable("category") String category,
                                                                               @PathVariable("language") String language) throws BadRequestException, NotFoundException {
        try {
            var resultDescription = service.findResultDescriptionByCategoryAndLanguage(category, language);
            var resultDescriptionDto = mapper.performMapping(resultDescription, ResultDescriptionDto.class);
            var message = String.format(DESCRIPTION_FOUND_MESSAGE, category);
            var response = createResponseWithStatusOK(message, resultDescriptionDto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (NumerologyCustomException exception) {
            throw new BadRequestException(BAD_REQUEST_MESSAGE, exception);
        } catch (NumerologyResultNotFoundException exception) {
            throw new NotFoundException(NOT_FOUND_MESSAGE, exception);
        }
    }

    private Response createResponseWithStatusOK(String message, Object detail) {
        return new Response(HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                message,
                detail);
    }
}
