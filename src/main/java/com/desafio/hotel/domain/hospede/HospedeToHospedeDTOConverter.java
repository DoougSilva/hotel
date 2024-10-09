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

        HospedeDTO hospedeDTO = new HospedeDTO();
        hospedeDTO.setId(source.getId());
        hospedeDTO.setNome(source.getNome());
        hospedeDTO.setDocumento(source.getDocumento());
        hospedeDTO.setTelefone(source.getTelefone());
        return hospedeDTO;
    }
}
