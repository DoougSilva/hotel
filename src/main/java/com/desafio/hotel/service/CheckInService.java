package com.desafio.hotel.service;

import com.desafio.hotel.utils.DateUtil;
import com.desafio.hotel.domain.base.EntityId;
import com.desafio.hotel.domain.checkin.CheckIn;
import com.desafio.hotel.domain.checkin.CheckInDTO;
import com.desafio.hotel.domain.hospede.Hospede;
import com.desafio.hotel.repository.CheckInRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class CheckInService {

    private final CheckInRepository repository;
    private final HospedeService hospedeService;
    private final ConversionService conversionService;

    private static final Double BUSINESS_DAY_PRICE = 120.00;
    private static final Double WEEKEND_DAY_PRICE = 150.00;
    private static final Double ADDITIONAL_BUSINESS_DAY = 15.00;
    private static final Double ADDITIONAL_WEEKEND_DAY = 15.00;
    private static final LocalTime CUT_OF_TIME = LocalTime.of(16, 30);

    @Transactional
    public EntityId create(CheckInDTO dto) {
        Hospede hospede = hospedeService.findByFields(dto.getHospede());
        CheckIn entity = conversionService.convert(dto, CheckIn.class);
        entity.setHospede(hospede);
        entity.setValor(calculateAmount(entity));
        return EntityId.of(repository.save(entity).getId());
    }

    private Double calculateAmount(CheckIn checkIn) {
        int businessDays = DateUtil.calculateBusinessDays(checkIn.getDataEntrada(), checkIn.getDataSaida());
        int weekendDays = DateUtil.calculateWeekend(checkIn.getDataEntrada(), checkIn.getDataSaida());

        Double amount = (businessDays * BUSINESS_DAY_PRICE) + (weekendDays * WEEKEND_DAY_PRICE);

        if (Boolean.TRUE.equals(checkIn.getAdicionalVeiculo())) {
            Double additionalAmount = (businessDays * ADDITIONAL_BUSINESS_DAY) + (weekendDays * ADDITIONAL_WEEKEND_DAY);
            amount += additionalAmount;
        }

        if (checkIn.getDataSaida().toLocalTime().isAfter(CUT_OF_TIME)) {
            amount += additionalDailyRate(checkIn.getDataSaida());
        }

        return amount;
    }

    private Double additionalDailyRate(LocalDateTime date) {
            boolean weekend = DateUtil.isWeekend(date.getDayOfWeek());
            return weekend ? WEEKEND_DAY_PRICE : BUSINESS_DAY_PRICE;
    }
}
