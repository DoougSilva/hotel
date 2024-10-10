package com.desafio.hotel.specification;

public interface Specification<T> {

    boolean isSatisfiedBy(T var);

    String getMessage();

    String getField();
}
