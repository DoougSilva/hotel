package com.desafio.hotel.domain.checkin;

import com.desafio.hotel.domain.hospede.HospedeDTO;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckInDTO {

    private UUID id;

    @NotNull
    private LocalDateTime dataEntrada;

    @NotNull
    private LocalDateTime dataSaida;

    private Boolean adicionalVeiculo = Boolean.FALSE;

    private Double valor;

    @NotNull
    private HospedeDTO hospede;
}
