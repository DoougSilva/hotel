package com.desafio.hotel.service;

import com.desafio.hotel.domain.hospede.HospedeDTO;
import com.desafio.hotel.repository.HospedeRepository;
import com.desafio.hotel.specification.GenericSpecification;

public class HospedeServiceSpecification {

    private HospedeServiceSpecification() {
    }

    public static GenericSpecification<HospedeDTO> validateTelefone(HospedeRepository repository) {
        return new GenericSpecification<HospedeDTO>() {
            @Override
            public boolean isSatisfiedBy(HospedeDTO hospedeDTO) {
                setMessage("Telefone já cadastrado!");

                return !repository.existsByTelefone(hospedeDTO.getTelefone());
            }
        };
    }

    public static GenericSpecification<HospedeDTO> validateDocumento(HospedeRepository repository) {
        return new GenericSpecification<HospedeDTO>() {
            @Override
            public boolean isSatisfiedBy(HospedeDTO hospedeDTO) {
                setMessage("Docuemnto já cadastrado!");

                return !repository.existsByDocumento(hospedeDTO.getDocumento());
            }
        };
    }
}
