package com.desafio.hotel.service;

import com.desafio.hotel.domain.hospede.HospedeDTO;
import com.desafio.hotel.exceptions.HospedeDuplicateResultException;
import com.desafio.hotel.specification.SpecificationValidator;
import com.desafio.hotel.utils.AmountUtil;
import com.desafio.hotel.domain.checkin.CheckIn;
import com.desafio.hotel.domain.checkin.CheckInDTO;
import com.desafio.hotel.domain.hospede.Hospede;
import com.desafio.hotel.repository.CheckInRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CheckInService {

    private final CheckInRepository repository;
    private final HospedeService hospedeService;
    private final ConversionService conversionService;

    @Transactional
    public CheckIn create(CheckInDTO dto) {
        log.info("Create CheckIn {}", dto);
        validate(dto);
        Hospede hospede = getHospede(dto.getHospede());
        CheckIn entity = conversionService.convert(dto, CheckIn.class);
        entity.setHospede(hospede);
        entity.setValor(AmountUtil.calculateAmount(entity));
        return repository.save(entity);
    }

    private Hospede getHospede(HospedeDTO dto) {
        try {
            return hospedeService.findByFields(dto);
        } catch (IncorrectResultSizeDataAccessException e) {
            log.error(e.getMessage());
            throw new HospedeDuplicateResultException();
        }
    }

    private void validate(CheckInDTO checkInDTO) {
        SpecificationValidator.of()
                .add(CheckInServiceSpecification.validateDataEntrada())
                .add(CheckInServiceSpecification.validateDataSaida())
                .validateWithException(checkInDTO);
    }
}
