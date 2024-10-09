package com.desafio.hotel.utils;

import lombok.experimental.UtilityClass;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@UtilityClass
public class DateUtil {

    public int calculateWeekend(LocalDateTime dataInicio, LocalDateTime dataFim) {
        int count = 0;

        while (!dataInicio.isAfter(dataFim) && !dataInicio.equals(dataFim)) {
            if (dataInicio.getDayOfWeek().equals(DayOfWeek.SATURDAY) || dataInicio.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                count++;
            }
            dataInicio = dataInicio.plusDays(1);
        }
        return count;
    }

    public int calculateBusinessDays(LocalDateTime dataInicio, LocalDateTime dataFim) {
        int count = 0;

        while (!dataInicio.isAfter(dataFim) && !dataInicio.equals(dataFim)) {
            if (!dataInicio.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !dataInicio.getDayOfWeek().equals(DayOfWeek.SUNDAY) ) {
                count++;
            }
            dataInicio = dataInicio.plusDays(1);
        }

        return count;
    }

    public boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY);
    }
}
