package com.desafio.hotel.service;

import com.desafio.hotel.domain.hospede.Hospede;
import com.desafio.hotel.domain.hospede.HospedeDTO;
import com.desafio.hotel.repository.HospedeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HospedeService {

    private final HospedeRepository repository;
    private final ConversionService conversionService;

    @Transactional
    public Hospede create(HospedeDTO dto) {
        Hospede entity = conversionService.convert(dto, Hospede.class);
        return repository.save(entity);
    }

    @Transactional(readOnly = true)
    public HospedeDTO findById(UUID uuid) {
        return conversionService.convert(findOne(uuid), HospedeDTO.class);
    }

    @Transactional(readOnly = true)
    public Page<HospedeDTO> findAll(PageRequest pageRequest) {
        Page<Hospede> page = repository.findAll(pageRequest);
        return page.map(hospede -> conversionService.convert(hospede, HospedeDTO.class));
    }

    @Transactional
    public Hospede update(HospedeDTO dto) {
        if (!existsEntity(dto)) {
            throw new RuntimeException();
        }
        Hospede entity = conversionService.convert(dto, Hospede.class);
        return repository.save(entity);
    }

    @Transactional
    public void delete(UUID uuid) {
        repository.deleteById(uuid);
    }

    private Hospede findOne(UUID uuid) {
        return repository.findById(uuid).orElseThrow();
    }

    private boolean existsEntity(HospedeDTO dto) {
        if (Objects.nonNull(dto.getId())) {
            return repository.existsById(dto.getId());
        }
        return Boolean.FALSE;
    }

    @Transactional(readOnly = true)
    public Hospede findByFields(HospedeDTO hospede) {
        return repository.findByFields(hospede.getNome(), hospede.getDocumento(), hospede.getTelefone())
                .orElseThrow();
    }

    @Transactional(readOnly = true)
    public Page<HospedeDTO> findAllByCheckInExpered(PageRequest pageRequest) {
        Page<Hospede> page = repository.findAllByCheckInsExpired(pageRequest);
        return page.map(hospede -> conversionService.convert(hospede, HospedeDTO.class));
    }
}
