package com.desafio.hotel.domain.checkin;

import com.desafio.hotel.domain.hospede.Hospede;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@Table(name = "tb_check_in")
@AllArgsConstructor
@NoArgsConstructor
public class CheckIn implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private UUID id;

    @NonNull
    @Column
    private LocalDateTime dataEntrada;

    @NonNull
    @Column
    private LocalDateTime dataSaida;

    @Column
    private Boolean adicionalVeiculo;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "hospede_id", nullable = false)
    private Hospede hospede;
}
