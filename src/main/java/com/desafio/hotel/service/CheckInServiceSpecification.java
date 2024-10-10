package com.desafio.hotel.service;

import com.desafio.hotel.domain.checkin.CheckInDTO;
import com.desafio.hotel.specification.GenericSpecification;

import java.time.LocalDateTime;

public class CheckInServiceSpecification {

    private CheckInServiceSpecification() {
    }

    public static GenericSpecification<CheckInDTO> validateDataSaida() {
        return new GenericSpecification<CheckInDTO>() {
            @Override
            public boolean isSatisfiedBy(CheckInDTO checkInDTO) {
                setMessage("Data invalida, a data de saida deve ser depois da data de entrada!");

                return checkInDTO.getDataSaida().isAfter(checkInDTO.getDataEntrada());
            }
        };
    }

    public static GenericSpecification<CheckInDTO> validateDataEntrada() {
        return new GenericSpecification<CheckInDTO>() {
            @Override
            public boolean isSatisfiedBy(CheckInDTO checkInDTO) {
                setMessage("Data invalida, a data de entrada não deve ser anterior a data atual!");

                return !checkInDTO.getDataEntrada().isBefore(LocalDateTime.now());
            }
        };
    }
}
