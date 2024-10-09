package com.desafio.hotel.config;

import com.desafio.hotel.domain.hospede.HospedeDTOToHospedeConverter;
import com.desafio.hotel.domain.hospede.HospedeToHospedeDTOConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;

@Configuration
public class ConversionConfig {

    @Bean
    public FormattingConversionService conversionService(HospedeToHospedeDTOConverter hospedeToHospedeDTO,
                                                         HospedeDTOToHospedeConverter hospedeDTOToHospede) {
        FormattingConversionService conversionService = new FormattingConversionService();
        conversionService.addConverter(hospedeToHospedeDTO);
        conversionService.addConverter(hospedeDTOToHospede);
        return conversionService;
    }
}
