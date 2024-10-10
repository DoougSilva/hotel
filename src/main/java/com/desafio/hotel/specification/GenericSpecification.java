package com.desafio.hotel.specification;


import com.desafio.hotel.exceptions.ValidateException;

public abstract class GenericSpecification<T> implements Specification<T> {

    private String message;
    private String field;

    public GenericSpecification(){}

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessage(String field, String message) {
        this.message = message;
        this.field = field;
    }

    public String getMessage() {
        return this.message;
    }

    public String getField() {
        return this.field;
    }

    public GenericSpecification<T> and(GenericSpecification<T> other) {
        return new AndSpecification(this, other);
    }

    public boolean isSatisfiedByWithException(T candidate) throws ValidateException {
        boolean result = this.isSatisfiedBy(candidate);
        if (!result){
            throw new ValidateException(this.message);
        } else {
            return true;
        }
    }
}
