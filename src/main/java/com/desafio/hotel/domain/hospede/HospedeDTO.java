package com.desafio.hotel.domain.hospede;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HospedeDTO {

    private UUID id;

    @NonNull
    private String nome;

    @NonNull
    private String documento;

    @NonNull
    private String telefone;

}
