package com.desafio.hotel.service;

import com.desafio.hotel.domain.hospede.Hospede;
import com.desafio.hotel.domain.hospede.HospedeDTO;
import com.desafio.hotel.exceptions.HospedeNotFoundException;
import com.desafio.hotel.repository.HospedeRepository;
import com.desafio.hotel.specification.SpecificationValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class HospedeService {

    private final HospedeRepository repository;
    private final ConversionService conversionService;

    @Transactional
    public Hospede create(HospedeDTO dto) {
        log.info("Creating hospede {}", dto);
        validateCreate(dto);
        Hospede entity = conversionService.convert(dto, Hospede.class);
        return repository.save(entity);
    }

    @Transactional(readOnly = true)
    public HospedeDTO findById(UUID uuid) {
        log.info("Finding hospede {}", uuid);
        return mapToHospedeDTO(findOne(uuid));
    }

    @Transactional(readOnly = true)
    public Page<HospedeDTO> findAll(PageRequest pageRequest) {
        log.info("Finding all hospedes");
        Page<Hospede> page = repository.findAll(pageRequest);
        return page.map(hospede -> mapToHospedeDTO(hospede));
    }

    @Transactional
    public Hospede update(HospedeDTO dto) {
        log.info("Updating hospede {}", dto);
        Hospede oldEntity = findOne(dto.getId());
        validateUpdate(dto, oldEntity);
        Hospede entity = conversionService.convert(dto, Hospede.class);
        return repository.save(entity);
    }

    @Transactional
    public void delete(UUID uuid) {
        log.info("Deleting hospede {}", uuid);
        if (!repository.existsById(uuid)) {
            log.error("Hospede {} not found", uuid);
            throw new HospedeNotFoundException();
        }
        repository.deleteById(uuid);
    }

    @Transactional(readOnly = true)
    public Hospede findByFields(HospedeDTO hospede) {
        log.info("Finding by fields hospede {}", hospede);
        return repository.findByFields(hospede.getNome(), hospede.getDocumento(), hospede.getTelefone())
                .orElseThrow(() -> new HospedeNotFoundException());
    }

    @Transactional(readOnly = true)
    public Page<HospedeDTO> findAllByCheckInExpered(PageRequest pageRequest) {
        log.info("Finding all hospedes by check-in-experied");
        Page<Hospede> page = repository.findAllByCheckInsExpired(pageRequest);
        return page.map(hospede -> mapToHospedeDTO(hospede));
    }

    @Transactional(readOnly = true)
    public Page<HospedeDTO> findAllWithCurrentCheckIn(PageRequest pageRequest) {
        log.info("Finding all hospedes with current check-in");
        Page<Hospede> page = repository.findAllWithCurrentCheckIn(pageRequest);
        return page.map(hospede -> mapToHospedeDTO(hospede));
    }

    private Hospede findOne(UUID uuid) {
        return repository.findById(uuid).orElseThrow(() -> new HospedeNotFoundException());
    }

    private HospedeDTO mapToHospedeDTO(Hospede hospede) {
        HospedeDTO hospedeDTO = conversionService.convert(hospede, HospedeDTO.class);
        hospedeDTO.setValores(hospede.getCheckIns());
        return hospedeDTO;
    }

    private void validateCreate(HospedeDTO hospedeDTO) {
        SpecificationValidator.of()
                .add(HospedeServiceSpecification.validateDocumento(repository))
                .add(HospedeServiceSpecification.validateTelefone(repository))
                .validateWithException(hospedeDTO);
    }

    private void validateUpdate(HospedeDTO hospedeDTO, Hospede hospede) {
        SpecificationValidator.of()
                .add(HospedeServiceSpecification.validateDocumentoUpdate(repository, hospede))
                .add(HospedeServiceSpecification.validateTelefoneUpdate(repository, hospede))
                .validateWithException(hospedeDTO);
    }
}
