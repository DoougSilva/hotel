package com.desafio.hotel.repository;

import com.desafio.hotel.domain.hospede.Hospede;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, UUID> {

    @Query("SELECT hospede FROM Hospede hospede WHERE hospede.nome = :nome " +
            "OR hospede.documento = :documento " +
            "OR hospede.telefone = :telefone")
    Optional<Hospede> findByFields(@Param("nome") String nome,
                                   @Param("documento") String documento,
                                   @Param("telefone") String telefone);

    @Query("SELECT hospede FROM Hospede hospede JOIN hospede.checkIns checkin WHERE checkin.dataSaida < CURRENT_TIMESTAMP")
    Page<Hospede> findAllByCheckInsExpired(PageRequest pageRequest);

    @Query("SELECT hospede FROM Hospede hospede JOIN hospede.checkIns checkin WHERE CURRENT_TIMESTAMP BETWEEN checkin.dataEntrada AND checkin.dataSaida")
    Page<Hospede> findAllWithCurrentCheckIn(PageRequest pageRequest);

    Boolean existsByTelefone(String telefone);

    Boolean existsByDocumento(String documento);

}
