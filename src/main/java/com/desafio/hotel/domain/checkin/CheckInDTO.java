package com.desafio.hotel.domain.checkin;

import com.desafio.hotel.domain.hospede.Hospede;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckInDTO {

    private UUID id;

    @NonNull
    private LocalDateTime dataEntrada;

    @NonNull
    private LocalDateTime dataSaida;

    private Boolean adicionalVeiculo;

    @NonNull
    private Hospede hospede;
}
