package com.desafio.hotel.checkin;

import com.desafio.hotel.domain.checkin.CheckInDTO;
import com.desafio.hotel.exceptions.ValidateException;
import com.desafio.hotel.hospede.HospedeTestHelper;
import com.desafio.hotel.service.CheckInServiceSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class CheckInServiceSpecificationUnitTest {

    private CheckInDTO checkInDTO;

    @BeforeEach
    void setUp() {
        checkInDTO = CheckInTestHelper.createCheckInDTO(HospedeTestHelper.createHospedeDTO());
    }

    @Test
    void shouldReturnTrueWhenDataSaidaIsAfterDataEntrada() {
        LocalDateTime dataEntrada = LocalDateTime.now();
        LocalDateTime dataSaida = LocalDateTime.now().plusDays(1);
        checkInDTO.setDataEntrada(dataEntrada);
        checkInDTO.setDataSaida(dataSaida);

        Assertions.assertTrue(CheckInServiceSpecification.validateDataSaida().isSatisfiedBy(checkInDTO));
    }

    @Test
    void shouldReturnFalseWhenDataSaidaIsBeforeDataEntrada() {
        LocalDateTime dataEntrada = LocalDateTime.now();
        LocalDateTime dataSaida = LocalDateTime.now().minusDays(1);
        checkInDTO.setDataEntrada(dataEntrada);
        checkInDTO.setDataSaida(dataSaida);

        Assertions.assertFalse(CheckInServiceSpecification.validateDataSaida().isSatisfiedBy(checkInDTO));
    }

    @Test
    void shouldThrowExceptionWhenDataSaidaIsBeforeDataEntrada() {
        LocalDateTime dataEntrada = LocalDateTime.now();
        LocalDateTime dataSaida = LocalDateTime.now().minusDays(1);
        checkInDTO.setDataEntrada(dataEntrada);
        checkInDTO.setDataSaida(dataSaida);

        Assertions.assertThrows(ValidateException.class, () ->
                CheckInServiceSpecification.validateDataSaida().isSatisfiedByWithException(checkInDTO));
    }

    @Test
    void shouldReturnTrueWhenDataEntradaisAfterCurrentData() {
        LocalDateTime dataEntrada = LocalDateTime.now().plusHours(1);
        checkInDTO.setDataEntrada(dataEntrada);

        Assertions.assertTrue(CheckInServiceSpecification.validateDataEntrada().isSatisfiedBy(checkInDTO));
    }

    @Test
    void shouldReturnFalseWhenDataEntradaIsBeforeCurrenteData() {
        LocalDateTime dataEntrada = LocalDateTime.now().minusHours(1);
        checkInDTO.setDataEntrada(dataEntrada);

        Assertions.assertFalse(CheckInServiceSpecification.validateDataEntrada().isSatisfiedBy(checkInDTO));
    }

    @Test
    void shouldThrowExceptionWhenDataEntradaIsBeforeCurrentData() {
        LocalDateTime dataEntrada = LocalDateTime.now().minusHours(1);
        checkInDTO.setDataEntrada(dataEntrada);

        Assertions.assertThrows(ValidateException.class, () ->
                CheckInServiceSpecification.validateDataEntrada().isSatisfiedByWithException(checkInDTO));
    }
}
