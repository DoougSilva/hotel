package com.desafio.hotel.exceptions;

import com.desafio.hotel.specification.ValidationMessage;
import jakarta.validation.ValidationException;

import java.util.Set;
import java.util.stream.Collectors;

public class ValidateException extends ValidationException {

    private Set<ValidationMessage> validationMessages;

    public ValidateException(Set<ValidationMessage> validationMessages) {
        super(validationMessages.stream().map(ValidationMessage::getMessage).collect(Collectors.joining(", ")));
        this.validationMessages = validationMessages;
    }

    public ValidateException(String message) {
        super(message);
    }
}
