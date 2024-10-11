package com.desafio.hotel.checkin;

import com.desafio.hotel.domain.checkin.CheckIn;
import com.desafio.hotel.domain.checkin.CheckInDTO;
import com.desafio.hotel.domain.hospede.Hospede;
import com.desafio.hotel.domain.hospede.HospedeDTO;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class CheckInTestHelper {

    public CheckIn createCheckIn(Hospede hospede) {
        return CheckIn.builder()
                .hospede(hospede)
                .dataEntrada(LocalDateTime.now().plusDays(1))
                .dataSaida(LocalDateTime.now().plusDays(2))
                .adicionalVeiculo(false)
                .build();
    }

    public CheckInDTO createCheckInDTO(HospedeDTO hospede) {
        return CheckInDTO.builder()
                .hospede(hospede)
                .dataEntrada(LocalDateTime.now().plusDays(1))
                .dataSaida(LocalDateTime.now().plusDays(2))
                .adicionalVeiculo(false)
                .build();
    }
}
