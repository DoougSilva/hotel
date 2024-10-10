package com.desafio.hotel.domain.checkin;

import com.desafio.hotel.domain.hospede.Hospede;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.io.Serializable;
import java.time.Instant;
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
    private UUID id;

    @NonNull
    private LocalDateTime dataEntrada;

    @NonNull
    private LocalDateTime dataSaida;

    private Boolean adicionalVeiculo;

    @NonNull
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "hospede_id", nullable = false)
    private Hospede hospede;

    @CreationTimestamp
    private Instant createdDate;
}
