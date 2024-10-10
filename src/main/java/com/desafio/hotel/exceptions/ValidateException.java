package com.desafio.hotel.exceptions;

import com.desafio.hotel.specification.ValidationMessage;
import jakarta.validation.ValidationException;

import java.util.Set;
import java.util.stream.Collectors;

public class ValidateException extends ValidationException {

    private Set<ValidationMessage> validationMessages;
    private Boolean hasError = false;
    public ValidateException(Set<ValidationMessage> validationMessages) {
        super((String)validationMessages.stream().map(ValidationMessage::getMessage).collect(Collectors.joining(", ")));
        this.validationMessages = validationMessages;
    }

    public Set<ValidationMessage> getValidationMessages() {
        return this.validationMessages;
    }

    public Boolean hasError() {
        return this.hasError;
    }

    public ValidateException() {
    }

    public ValidateException(String message) {
        super(message);
        this.hasError = true;
    }

    public ValidateException(Throwable cause) {
        super(cause);
    }

    public ValidateException(String message, Throwable cause) {
        super(message, cause);
    }
}
