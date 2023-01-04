package com.javathinked.application.numerology.generic;

public interface GenericNumerology<R, P> {

    R computeDestiny(P object);

    R computePersonality(P object);

    R computeAttitude(P object);

    R computeCharacter(P object);

    R computeSoulUrge(P object);

    R computeHiddenAgenda(P object);

    R computeDivinePurpose(P object);

    R computePersonalYear(P object);

    R computeLoveCompatibility(P firstObject, P secondObject);
}
