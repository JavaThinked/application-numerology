package com.javathinked.application.numerology.service;

import com.javathinked.application.numerology.repository.ResultDescriptionRepository;
import com.javathinked.application.numerology.service.core.NumerologyValue;
import com.javathinked.application.numerology.service.exception.NumerologyCustomException;
import com.javathinked.application.numerology.service.exception.NumerologyResultNotFoundException;
import com.javathinked.application.numerology.service.model.ResultDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ResultDescriptionService {

    private final ResultDescriptionRepository repository;

    @Autowired
    public ResultDescriptionService(ResultDescriptionRepository repository) {
        this.repository = repository;
    }

    public List<ResultDescription> findResultDescriptionsByLanguage(String language) {
        if(language.isEmpty()) {
            throw new NumerologyCustomException("The language can't be empty");
        }
        var descriptions = repository.findByLanguage(language);
        if(descriptions.isEmpty()) {
            throw new NumerologyResultNotFoundException("No descriptions found");
        }
        return descriptions;
    }

    public ResultDescription findResultDescriptionByCategoryAndLanguage(String category, String language) {
        if(category.isEmpty() || language.isEmpty()) {
            throw new NumerologyCustomException("The category or the language can't be empty");
        }

        if(Arrays.stream(NumerologyValue.Category.values()).noneMatch(c -> c.getCategory().equals(category))) {
            throw new NumerologyCustomException(String.format("The category %s is not in the list", category));
        }
        var resultDescription = repository.findByCategoryAndLanguage(category, language);
        return Optional.of(resultDescription).orElseThrow(() -> new NumerologyResultNotFoundException("No description found"));
    }
}
