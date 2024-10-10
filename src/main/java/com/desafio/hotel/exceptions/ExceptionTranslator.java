package com.desafio.hotel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler({ValidateException.class})
    public ResponseEntity<Problem> validateException(ValidateException exception){
        Problem problem = Problem.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .title("ValidateException")
                .build();
        return new ResponseEntity<>(problem, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HospedeDuplicateResultException.class})
    public ResponseEntity<Problem> hospedeDuplicateResultException(HospedeDuplicateResultException exception){
        Problem problem = Problem.builder()
                .status(HttpStatus.CONFLICT.value())
                .message(exception.getMessage())
                .title("HospedeDuplicateResultException")
                .build();
        return new ResponseEntity<>(problem, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({HospedeNotFoundException.class})
    public ResponseEntity<Problem> hospedeNotFoundException(HospedeNotFoundException exception){
        Problem problem = Problem.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .title("HospedeNotFoundException")
                .build();
        return new ResponseEntity<>(problem, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Problem> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        Problem problem = Problem.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(errors.toString())
                .title("ValidateException")
                .build();
        return new ResponseEntity<>(problem, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Problem> exception(Exception exception){
        Problem problem = Problem.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .title(exception.getClass().getName())
                .build();
        return new ResponseEntity<>(problem, HttpStatus.BAD_REQUEST);
    }
}
