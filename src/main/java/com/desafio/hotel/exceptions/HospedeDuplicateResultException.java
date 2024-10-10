package com.desafio.hotel.exceptions;

public class HospedeDuplicateResultException extends RuntimeException {

    public HospedeDuplicateResultException() {
        super("Mais de um Hospede encontrado com o valor informado! Tente outro campo.");
    }
}
