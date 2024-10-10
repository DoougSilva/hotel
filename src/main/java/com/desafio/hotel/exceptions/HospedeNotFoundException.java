package com.desafio.hotel.exceptions;

public class HospedeNotFoundException extends RuntimeException {

    public HospedeNotFoundException() {
        super("Hospede não encontrado!");
    }
}
