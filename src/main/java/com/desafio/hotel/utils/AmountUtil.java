package com.desafio.hotel.utils;

import com.desafio.hotel.domain.checkin.CheckIn;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.LocalTime;

@UtilityClass
public class AmountUtil {

    private static final Double BUSINESS_DAY_PRICE = 120.00;
    private static final Double WEEKEND_DAY_PRICE = 150.00;
    private static final Double ADDITIONAL_BUSINESS_DAY = 15.00;
    private static final Double ADDITIONAL_WEEKEND_DAY = 15.00;
    private static final LocalTime CUT_OF_TIME = LocalTime.of(16, 30);

    public Double calculateAmount(CheckIn checkIn) {
        int businessDays = DateUtil.calculateBusinessDays(checkIn.getDataEntrada(), checkIn.getDataSaida());
        int weekendDays = DateUtil.calculateWeekend(checkIn.getDataEntrada(), checkIn.getDataSaida());

        Double amount = (businessDays * BUSINESS_DAY_PRICE) + (weekendDays * WEEKEND_DAY_PRICE);

        if (Boolean.TRUE.equals(checkIn.getAdicionalVeiculo())) {
            Double additionalAmount = (businessDays * ADDITIONAL_BUSINESS_DAY) + (weekendDays * ADDITIONAL_WEEKEND_DAY);
            amount += additionalAmount;
        }

        if (checkIn.getDataSaida().toLocalTime().isAfter(CUT_OF_TIME)) {
            amount += additionalDailyRate(checkIn.getDataSaida(), checkIn.getAdicionalVeiculo());

        }

        return amount;
    }

    private Double additionalDailyRate(LocalDateTime date, boolean adicionalVeiculo) {
        boolean weekend = DateUtil.isWeekend(date.getDayOfWeek());

        if (Boolean.TRUE.equals(adicionalVeiculo)) {
           return weekend ? (WEEKEND_DAY_PRICE + ADDITIONAL_WEEKEND_DAY) : (BUSINESS_DAY_PRICE + ADDITIONAL_BUSINESS_DAY);
        }

        return weekend ? WEEKEND_DAY_PRICE : BUSINESS_DAY_PRICE;
    }
}
