package com.desafio.hotel.specification;

import com.desafio.hotel.exceptions.ValidateException;

import java.util.HashSet;
import java.util.Set;

public class SpecificationResult<T> {
    private Set<ValidationMessage> messages = new HashSet();

    public SpecificationResult() {
    }

    public void add(ValidationMessage validationMessage) {
        this.messages.add(validationMessage);
    }

    public void throwMessages() throws ValidateException {
        if (!this.messages.isEmpty()) {
            throw new ValidateException(this.messages);
        }
    }
}
