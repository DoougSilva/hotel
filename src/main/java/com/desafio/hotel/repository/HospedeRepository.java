package com.desafio.hotel.repository;

import com.desafio.hotel.domain.hospede.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, UUID> {

    @Query("SELECT a FROM Hospede a WHERE (:nome IS NULL OR a.nome = :nome) " +
            "OR (:documento IS NULL OR a.documento = :documento) " +
            "OR (:telefone) IS NULL OR a.telefone = :telefone")
    Optional<Hospede> findByFields(@Param("nome") String nome,
                                   @Param("documento") String documento,
                                   @Param("telefone") String telefone);

}
