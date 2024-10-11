package com.desafio.hotel.utils;

import com.desafio.hotel.domain.checkin.CheckIn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class AmountUtilUnitTest {

    private CheckIn checkIn;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;

    private void setCheckIn(LocalDateTime entrada, LocalDateTime saida, Boolean adicionalVeiculo) {
        checkIn = new CheckIn();
        checkIn.setDataEntrada(entrada);
        checkIn.setDataSaida(saida);
        checkIn.setAdicionalVeiculo(adicionalVeiculo);
    }

    @Test
    void shouldCalculateAmountWithOnlyBusinessDays() {
        dataEntrada = LocalDateTime.of(2024, 10, 10, 14, 0);
        dataSaida = LocalDateTime.of(2024, 10, 12, 16, 0);

        setCheckIn(dataEntrada, dataSaida, false);

        Double expectedAmount = 2 * 120.00;

        Double calculatedAmount = AmountUtil.calculateAmount(checkIn);
        Assertions.assertEquals(expectedAmount, calculatedAmount);
    }

    @Test
    void shouldCalculateAmountWithOnlyBusinessDaysAndAdditionalVehicle() {
        dataEntrada = LocalDateTime.of(2024, 10, 10, 14, 0);
        dataSaida = LocalDateTime.of(2024, 10, 12, 16, 0);

        setCheckIn(dataEntrada, dataSaida, true);

        Double expectedAmount = (2 * 120.00) + (2 * 15.00);

        Double calculatedAmount = AmountUtil.calculateAmount(checkIn);
        Assertions.assertEquals(expectedAmount, calculatedAmount);
    }

    @Test
    void shouldCalculateAmountWithOnlyWeekendDays() {
        dataEntrada = LocalDateTime.of(2024, 10, 12, 14, 0);
        dataSaida = LocalDateTime.of(2024, 10, 14, 16, 0);

        setCheckIn(dataEntrada, dataSaida, false);

        Double expectedAmount = 2 * 150.00;

        Double calculatedAmount = AmountUtil.calculateAmount(checkIn);
        Assertions.assertEquals(expectedAmount, calculatedAmount);
    }

    @Test
    void shouldCalculateAmountWithOnlyWeekendDaysAndAdditionalVehicle() {
        dataEntrada = LocalDateTime.of(2024, 10, 12, 14, 0);
        dataSaida = LocalDateTime.of(2024, 10, 14, 16, 0);

        setCheckIn(dataEntrada, dataSaida, true);;

        Double expectedAmount = (2 * 150.00) + (2 * 15.00);

        Double calculatedAmount = AmountUtil.calculateAmount(checkIn);
        Assertions.assertEquals(expectedAmount, calculatedAmount);
    }

    @Test
    void shouldCalculateAmountWithBusinessDaysAndWeekendDays() {
        dataEntrada = LocalDateTime.of(2024, 10, 9, 14, 0);
        dataSaida = LocalDateTime.of(2024, 10, 14, 16, 0);

        setCheckIn(dataEntrada, dataSaida, false);

        Double expectedAmount = (3 * 120.00) + (2 * 150.00);

        Double calculatedAmount = AmountUtil.calculateAmount(checkIn);
        Assertions.assertEquals(expectedAmount, calculatedAmount);
    }

    @Test
    void shouldCalculateAmountWithBusinessDaysAndWeekendDaysAndAdditionalVehicle() {
        dataEntrada = LocalDateTime.of(2024, 10, 9, 14, 0);
        dataSaida = LocalDateTime.of(2024, 10, 14, 16, 0);

        setCheckIn(dataEntrada, dataSaida, true);

        Double expectedAmount = (3 * 120.00) + (2 * 150.00) + (3 * 15.00) + (2 * 15.00);

        Double calculatedAmount = AmountUtil.calculateAmount(checkIn);
        Assertions.assertEquals(expectedAmount, calculatedAmount);
    }

    @Test
    void shouldCalculateAmountWithBusinessDaysAndLateCheckout() {
        dataEntrada = LocalDateTime.of(2024, 10, 8, 14, 0);
        dataSaida = LocalDateTime.of(2024, 10, 11, 17, 0);

        setCheckIn(dataEntrada, dataSaida, false);


        Double expectedAmount = (3 * 120.00) + 120.00;

        Double calculatedAmount = AmountUtil.calculateAmount(checkIn);
        Assertions.assertEquals(expectedAmount, calculatedAmount);
    }

    @Test
    void shouldCalculateAmountWithWeekendDaysAndLateCheckout() {
        dataEntrada = LocalDateTime.of(2024, 10, 12, 14, 0);
        dataSaida = LocalDateTime.of(2024, 10, 13, 17, 0);

        setCheckIn(dataEntrada, dataSaida, false);

        Double expectedAmount = (1 * 150.00) + 150.00;

        Double calculatedAmount = AmountUtil.calculateAmount(checkIn);
        Assertions.assertEquals(expectedAmount, calculatedAmount);
    }

    @Test
    void shouldCalculateAmountWithBusinessDaysAndLateCheckoutAndAdditionalVehicle() {
        dataEntrada = LocalDateTime.of(2024, 10, 7, 14, 0);
        dataSaida = LocalDateTime.of(2024, 10, 10, 17, 0);

        setCheckIn(dataEntrada, dataSaida, true);

        Double expectedAmount = (3 * 120.00) + (4 * 15.00) + 120.00;

        Double calculatedAmount = AmountUtil.calculateAmount(checkIn);
        Assertions.assertEquals(expectedAmount, calculatedAmount);
    }

    @Test
    void shouldCalculateAmountWithWeekendDaysAndLateCheckoutAndAdditionalVehicle() {
        dataEntrada = LocalDateTime.of(2024, 10, 12, 14, 0);
        dataSaida = LocalDateTime.of(2024, 10, 13, 17, 0);

        setCheckIn(dataEntrada, dataSaida, true);


        Double expectedAmount = (1 * 150.00) + (2 * 15.00) + 150.00;

        Double calculatedAmount = AmountUtil.calculateAmount(checkIn);
        Assertions.assertEquals(expectedAmount, calculatedAmount);
    }
}
