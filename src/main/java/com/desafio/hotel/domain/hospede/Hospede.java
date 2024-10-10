package com.desafio.hotel.domain.hospede;

import com.desafio.hotel.domain.checkin.CheckIn;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@Table(name = "tb_hospede")
@AllArgsConstructor
@NoArgsConstructor
public class Hospede implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NonNull
    private String nome;

    @NonNull
    private String documento;

    @NonNull
    private String telefone;

    @OneToMany(mappedBy = "hospede", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<CheckIn> checkIns;
}
