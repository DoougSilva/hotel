package com.desafio.hotel.utils;

import lombok.experimental.UtilityClass;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@UtilityClass
public class DateUtil {

    public int calculateWeekend(LocalDateTime dataInicio, LocalDateTime dataFim) {
        int count = 0;

        if (dataInicio.toLocalDate().isEqual(dataFim.toLocalDate()) && isWeekend(dataInicio.getDayOfWeek())) {
            return 1;
        }

        while (dataInicio.toLocalDate().isBefore(dataFim.toLocalDate())) {
            if (isWeekend(dataInicio.getDayOfWeek())) {
                count++;
            }
            dataInicio = dataInicio.plusDays(1);
        }
        return count;
    }

    public int calculateBusinessDays(LocalDateTime dataInicio, LocalDateTime dataFim) {
        int count = 0;

        if (dataInicio.toLocalDate().isEqual(dataFim.toLocalDate()) && !isWeekend(dataInicio.getDayOfWeek())) {
            return 1;
        }

        while (dataInicio.toLocalDate().isBefore(dataFim.toLocalDate())) {
            if (!isWeekend(dataInicio.getDayOfWeek())) {
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
