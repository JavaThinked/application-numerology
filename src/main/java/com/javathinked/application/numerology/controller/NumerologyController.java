package com.javathinked.application.numerology.controller;

import com.javathinked.application.numerology.controller.dto.FinalResultDto;
import com.javathinked.application.numerology.controller.dto.PersonDto;
import com.javathinked.application.numerology.controller.dto.ResultDto;
import com.javathinked.application.numerology.controller.exception.BadRequestException;
import com.javathinked.application.numerology.controller.exception.NotFoundException;
import com.javathinked.application.numerology.service.NumerologyService;
import com.javathinked.application.numerology.service.core.NumerologyValue;
import com.javathinked.application.numerology.service.exception.NumerologyResultNotFoundException;
import com.javathinked.application.numerology.service.exception.NumerologyValidationException;
import com.javathinked.application.numerology.service.model.Person;
import com.javathinked.application.numerology.util.MapperWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.javathinked.application.numerology.service.core.NumerologyValue.Category.*;

@RestController
@RequestMapping("/v1/numerology")
public class NumerologyController {

    public static final String BAD_REQUEST_MESSAGE = "An error occurs while computing the %s";
    public static final String NOT_FOUND_MESSAGE = "The result is not found for the %s";
    private final NumerologyService service;
    private final MapperWrapper mapper;

    @Autowired
    public NumerologyController(NumerologyService service, MapperWrapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("/destiny")
    public ResponseEntity<Response> computeDestiny(@Valid @RequestBody PersonDto personDto) throws BadRequestException, NotFoundException {
        return computeNumber(DESTINY, personDto);
    }

    @PostMapping("/personality")
    public ResponseEntity<Response> computePersonality(@Valid @RequestBody PersonDto personDto) throws BadRequestException, NotFoundException {
        return computeNumber(PERSONALITY, personDto);
    }

    @PostMapping("/attitude")
    public ResponseEntity<Response> computeAttitude(@Valid @RequestBody PersonDto personDto) throws BadRequestException, NotFoundException {
        return computeNumber(ATTITUDE, personDto);
    }

    @PostMapping("/character")
    public ResponseEntity<Response> computeCharacter(@Valid @RequestBody PersonDto personDto) throws BadRequestException, NotFoundException {
        return computeNumber(CHARACTER, personDto);
    }

    @PostMapping("/soul-urge")
    public ResponseEntity<Response> computeSoulUrge(@Valid @RequestBody PersonDto personDto) throws BadRequestException, NotFoundException {
        return computeNumber(SOUL_URGE, personDto);
    }

    @PostMapping("/hidden-agenda")
    public ResponseEntity<Response> computeHiddenAgenda(@Valid @RequestBody PersonDto personDto) throws BadRequestException, NotFoundException {
        return computeNumber(HIDDEN_AGENDA, personDto);
    }

    @PostMapping("/divine-purpose")
    public ResponseEntity<Response> computeDivinePurpose(@Valid @RequestBody PersonDto personDto) throws BadRequestException, NotFoundException {
        return computeNumber(DIVINE_PURPOSE, personDto);
    }

    @PostMapping("/personal-year")
    public ResponseEntity<Response> computePersonalYear(@Valid @RequestBody PersonDto personDto) throws BadRequestException, NotFoundException {
        return computeNumber(PERSONAL_YEAR, personDto);
    }

    @PostMapping("/love-compatibility")
    public ResponseEntity<Response> computeLoveCompatibility(@Valid @RequestBody PersonDto[] personsDto) throws BadRequestException, NotFoundException {
        try {
            var firstPersonDto = personsDto[0];
            var secondPersonDto = personsDto[1];
            var firstPerson = mapper.performMapping(firstPersonDto, Person.class);
            var secondPerson = mapper.performMapping(secondPersonDto, Person.class);
            var resultDto = mapper.performMapping(service.computeNumber(LOVE_COMPATIBILITY, firstPerson, secondPerson), ResultDto.class);
            var response = createResponseWithStatusOK(LOVE_COMPATIBILITY, resultDto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (NumerologyValidationException exception) {
            throw new BadRequestException(String.format(BAD_REQUEST_MESSAGE, LOVE_COMPATIBILITY.getDescription()), exception);
        } catch (NumerologyResultNotFoundException exception) {
            throw new NotFoundException(String.format(NOT_FOUND_MESSAGE, LOVE_COMPATIBILITY.getDescription()), exception);
        }
    }

    @PostMapping("/final-result")
    public ResponseEntity<Response> computeFinalResult(@Valid @RequestBody PersonDto[] personsDto) throws BadRequestException, NotFoundException {
        try {
            var firstPersonDto = personsDto[0];
            var secondPersonDto = personsDto[1];
            var firstPerson = mapper.performMapping(firstPersonDto, Person.class);
            var secondPerson = mapper.performMapping(secondPersonDto, Person.class);
            var finalResultDto = mapper.performMapping((service).getFinalResult(firstPerson, secondPerson), FinalResultDto.class);
            var response = createResponseWithStatusOKForFinalResult(FINAL_RESULT, finalResultDto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (NumerologyValidationException exception) {
            throw new BadRequestException(String.format(BAD_REQUEST_MESSAGE, FINAL_RESULT.getDescription()), exception);
        } catch (NumerologyResultNotFoundException exception) {
            throw new NotFoundException(String.format(NOT_FOUND_MESSAGE, FINAL_RESULT.getDescription()), exception);
        }
    }

    private ResponseEntity<Response> computeNumber(NumerologyValue.Category category, PersonDto personDto) throws BadRequestException, NotFoundException {
        try {
            var person = mapper.performMapping(personDto, Person.class);
            var resultDto = mapper.performMapping(service.computeNumber(category, person), ResultDto.class);
            var response = createResponseWithStatusOK(category, resultDto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (NumerologyValidationException exception) {
            throw new BadRequestException(String.format(BAD_REQUEST_MESSAGE, category.getDescription()), exception);
        } catch (NumerologyResultNotFoundException exception) {
            throw new NotFoundException(String.format(NOT_FOUND_MESSAGE, category.getDescription()), exception);
        }
    }

    private Response createResponseWithStatusOK(NumerologyValue.Category category, ResultDto resultDto) {
        return new Response(HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                category.getDescription(),
                resultDto);
    }

    private Response createResponseWithStatusOKForFinalResult(NumerologyValue.Category category, FinalResultDto finalResultDto) {
        return new Response(HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                category.getDescription(),
                finalResultDto);
    }
}
