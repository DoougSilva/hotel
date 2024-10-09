package com.desafio.hotel.domain.hospede.converters;

import com.desafio.hotel.domain.hospede.Hospede;
import com.desafio.hotel.domain.hospede.HospedeDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class HospedeDTOToHospedeConverter implements Converter<HospedeDTO, Hospede> {

    @Override
    public Hospede convert(HospedeDTO source) {
        if (source == null) {
            return null;
        }

        Hospede hospede = new Hospede();
        hospede.setId(source.getId());
        hospede.setNome(source.getNome());
        hospede.setDocumento(source.getDocumento());
        hospede.setTelefone(source.getTelefone());
        return hospede;
    }
}
