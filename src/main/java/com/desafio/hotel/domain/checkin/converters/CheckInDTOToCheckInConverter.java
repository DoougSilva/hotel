package com.desafio.hotel.domain.checkin.converters;

import com.desafio.hotel.domain.checkin.CheckIn;
import com.desafio.hotel.domain.checkin.CheckInDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CheckInDTOToCheckInConverter implements Converter<CheckInDTO, CheckIn> {

    @Override
    public CheckIn convert(CheckInDTO source) {
        if (source == null) {
            return null;
        }
        CheckIn checkIn = new CheckIn();
        checkIn.setId(source.getId());
        checkIn.setDataEntrada(source.getDataEntrada());
        checkIn.setDataSaida(source.getDataSaida());
        checkIn.setAdicionalVeiculo(source.getAdicionalVeiculo());
        return checkIn;
    }
}
