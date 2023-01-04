package com.javathinked.application.numerology.util;

public interface MapperWrapper {

    <T> T performMapping(Object source, Class<T> destination);
}
