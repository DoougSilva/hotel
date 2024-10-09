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

    @Query("SELECT hospede FROM Hospede hospede WHERE (:nome IS NULL OR hospede.nome = :nome) " +
            "OR (:documento IS NULL OR hospede.documento = :documento) " +
            "OR (:telefone) IS NULL OR hospede.telefone = :telefone")
    Optional<Hospede> findByFields(@Param("nome") String nome,
                                   @Param("documento") String documento,
                                   @Param("telefone") String telefone);

    @Query("SELECT hospede FROM Hospede hospede JOIN hospede.checkIns checkin WHERE checkin.dataSaida < CURRENT_TIMESTAMP")
    Page<Hospede> findAllByCheckInsExpired(PageRequest pageRequest);
}
