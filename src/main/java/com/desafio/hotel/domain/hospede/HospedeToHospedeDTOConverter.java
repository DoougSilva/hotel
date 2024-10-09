package com.desafio.hotel.domain.hospede;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class HospedeToHospedeDTOConverter implements Converter<Hospede, HospedeDTO> {

    @Override
    public HospedeDTO convert(Hospede source) {
        if (source == null) {
            return null;
        }
        return HospedeDTO.builder()
                .id(source.getId())
                .nome(source.getNome())
                .documento(source.getDocumento())
                .telefone(source.getTelefone())
                .checkIns(source.getCheckIns())
                .build();
    }
}
