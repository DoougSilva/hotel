package com.desafio.hotel.domain.hospede;

import com.desafio.hotel.domain.checkin.CheckIn;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HospedeDTO {

    private UUID id;

    @NotBlank
    private String nome;

    @NotBlank
    private String documento;

    @NotBlank
    private String telefone;

    private Double valorUltimaHospedagem;

    private Double valorTotalHospedagem;

    public void setValores(List<CheckIn> checkIns) {
        if (!CollectionUtils.isEmpty(checkIns)) {
            Double lastAmount = Collections.max(checkIns, Comparator.comparing(CheckIn::getCreatedDate)).getValor();
            Double totalAmount = checkIns.stream().mapToDouble(CheckIn::getValor).sum();

            this.setValorUltimaHospedagem(lastAmount);
            this.setValorTotalHospedagem(totalAmount);
        }
    }

}
