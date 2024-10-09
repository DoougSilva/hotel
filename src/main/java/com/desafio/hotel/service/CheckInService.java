package com.desafio.hotel.service;

import com.desafio.hotel.domain.base.EntityId;
import com.desafio.hotel.domain.checkin.CheckIn;
import com.desafio.hotel.domain.checkin.CheckInDTO;
import com.desafio.hotel.domain.hospede.Hospede;
import com.desafio.hotel.repository.CheckInRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CheckInService {

    private final CheckInRepository repository;
    private final HospedeService hospedeService;
    private final ConversionService conversionService;

    @Transactional
    public EntityId create(CheckInDTO dto) {
        Hospede hospede = hospedeService.findByFields(dto.getHospede());
        CheckIn entity = conversionService.convert(dto, CheckIn.class);
        entity.setHospede(hospede);
        return EntityId.of(repository.save(entity).getId());
    }
}
