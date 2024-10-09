package com.desafio.hotel.repository;

import com.desafio.hotel.domain.hospede.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, UUID> {
}
