package com.javathinked.application.numerology.service;

import com.javathinked.application.numerology.repository.ResultRepository;
import com.javathinked.application.numerology.service.core.NumerologyComputer;
import com.javathinked.application.numerology.service.core.NumerologyModelValidator;
import com.javathinked.application.numerology.service.core.NumerologyValue;
import com.javathinked.application.numerology.service.exception.NumerologyResultNotFoundException;
import com.javathinked.application.numerology.service.exception.NumerologyValidationException;
import com.javathinked.application.numerology.service.model.FinalResult;
import com.javathinked.application.numerology.service.model.Person;
import com.javathinked.application.numerology.service.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

import static com.javathinked.application.numerology.service.core.NumerologyValue.Category.*;

@Service("NumerologyService")
public class NumerologyService {

    public static final String NO_RESULT_FOUND_MESSAGE = "No record found for result %s";
    private final Map<String, NumerologyComputer> computerMap;
    private final ResultRepository repository;
    private final NumerologyModelValidator<Person> validator;

    @Autowired
    public NumerologyService(Map<String, NumerologyComputer> computerMap, ResultRepository repository, NumerologyModelValidator<Person> validator) {
        this.computerMap = computerMap;
        this.repository = repository;
        this.validator = validator;
    }

    public Result computeNumber(NumerologyValue.Category category, Person... person) {
        Result result;
        if(category != LOVE_COMPATIBILITY) {
            doesModelIsValid(person[0]);
            result = repository.findResultByCategoryAndNumberAndLanguage(category.getCategory(),
                    computerMap.get(category.getBeanName()).computeNumberFor(person[0]),
                    person[0].getLanguage());
        } else {
            doesModelIsValid(person[0]);
            doesModelIsValid(person[1]);
            result = repository.findResultByCategoryAndNumberAndLanguage(category.getCategory(),
                    computerMap.get(category.getBeanName()).computeNumberFor(person[0], person[1]),
                    person[0].getLanguage());
        }
        return Optional.of(result).orElseThrow(() -> new NumerologyResultNotFoundException(String.format(NO_RESULT_FOUND_MESSAGE, category.getDescription())));
    }

    public FinalResult getFinalResult(Person firstPerson, Person secondPerson) {
        return new FinalResult(computeNumber(DESTINY, firstPerson),
                computeNumber(PERSONALITY, firstPerson),
                computeNumber(ATTITUDE, firstPerson),
                computeNumber(CHARACTER, firstPerson),
                computeNumber(SOUL_URGE, firstPerson),
                computeNumber(HIDDEN_AGENDA, firstPerson),
                computeNumber(DIVINE_PURPOSE, firstPerson),
                computeNumber(PERSONAL_YEAR, firstPerson),
                computeNumber(LOVE_COMPATIBILITY, firstPerson, secondPerson));
    }

    private void doesModelIsValid(Person person) {
        var validations = validator.getConstrainsViolations(person);
        if (!validations.isEmpty()) {
            throw new NumerologyValidationException(validations);
        }
    }
}
