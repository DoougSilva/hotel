package com.desafio.hotel.domain.hospede;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class HospedeDTOToHospedeConverter implements Converter<HospedeDTO, Hospede> {

    @Override
    public Hospede convert(HospedeDTO source) {
        if (source == null) {
            return null;
        }
        return Hospede.builder()
                .id(source.getId())
                .nome(source.getNome())
                .documento(source.getDocumento())
                .telefone(source.getTelefone())
                .checkIns(source.getCheckIns())
                .build();
    }
}
