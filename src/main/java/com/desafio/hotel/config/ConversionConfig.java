package com.desafio.hotel.config;

import com.desafio.hotel.domain.checkin.converters.CheckInDTOToCheckInConverter;
import com.desafio.hotel.domain.hospede.converters.HospedeDTOToHospedeConverter;
import com.desafio.hotel.domain.hospede.converters.HospedeToHospedeDTOConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;

@Configuration
public class ConversionConfig {

    @Bean
    public FormattingConversionService conversionService(HospedeToHospedeDTOConverter hospedeToHospedeDTO,
                                                         HospedeDTOToHospedeConverter hospedeDTOToHospede,
                                                         CheckInDTOToCheckInConverter checkInDTOToCheckInConverter) {
        FormattingConversionService conversionService = new FormattingConversionService();
        conversionService.addConverter(hospedeToHospedeDTO);
        conversionService.addConverter(hospedeDTOToHospede);
        conversionService.addConverter(checkInDTOToCheckInConverter);
        return conversionService;
    }
}
