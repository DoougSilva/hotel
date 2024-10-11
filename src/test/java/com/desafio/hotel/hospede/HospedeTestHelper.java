package com.desafio.hotel.hospede;

import com.desafio.hotel.domain.hospede.Hospede;
import com.desafio.hotel.domain.hospede.HospedeDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HospedeTestHelper {

    public HospedeDTO createHospedeDTO() {
        return HospedeDTO.builder()
                .nome("Fulano da Silva")
                .documento("123.456.789-00")
                .telefone("(11) 9 9988-7766")
                .build();
    }

    public Hospede createHospede() {
        return Hospede.builder()
                .nome("Fulano da Silva")
                .documento("123.456.789-00")
                .telefone("(11) 9 9988-7766")
                .build();
    }
}
