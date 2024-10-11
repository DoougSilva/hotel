package com.desafio.hotel.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class DateUtilUnitTest {

    @Test
    void shouldCalculateWeekend() {
        LocalDateTime dataEntrada = LocalDateTime.of(2024, 10, 7, 12, 0);
        LocalDateTime dataSaida = LocalDateTime.of(2024, 10, 21, 12, 0);

        Assertions.assertEquals(4, DateUtil.calculateWeekend(dataEntrada, dataSaida));
    }

    @Test
    void shouldCalculateBusinessDay() {
        LocalDateTime dataEntrada = LocalDateTime.of(2024, 10, 7, 12, 0);
        LocalDateTime dataSaida = LocalDateTime.of(2024, 10, 21, 12, 0);

        Assertions.assertEquals(10, DateUtil.calculateBusinessDays(dataEntrada, dataSaida));
    }

    @Test
    void shouldReturnTrueWhenDayofWeekIsWeekend() {
        Assertions.assertTrue(DateUtil.isWeekend(DayOfWeek.SATURDAY));
        Assertions.assertTrue(DateUtil.isWeekend(DayOfWeek.SUNDAY));
    }

    @Test
    void shouldReturnFalseWhenDayofWeekIsBusinessDay() {
        Assertions.assertFalse(DateUtil.isWeekend(DayOfWeek.MONDAY));
        Assertions.assertFalse(DateUtil.isWeekend(DayOfWeek.TUESDAY));
        Assertions.assertFalse(DateUtil.isWeekend(DayOfWeek.WEDNESDAY));
        Assertions.assertFalse(DateUtil.isWeekend(DayOfWeek.THURSDAY));
        Assertions.assertFalse(DateUtil.isWeekend(DayOfWeek.FRIDAY));
    }
}
