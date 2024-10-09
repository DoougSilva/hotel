package com.desafio.hotel.domain.hospede;

import com.desafio.hotel.domain.checkin.CheckIn;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;
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
    @Column
    private UUID id;

    @NonNull
    @Column
    private String nome;

    @NonNull
    @Column
    private String documento;

    @NonNull
    @Column
    private String telefone;

    @OneToMany(mappedBy = "hospede")
    private Set<CheckIn> checkIns;
}
