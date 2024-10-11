package com.desafio.hotel.hospede;

import com.desafio.hotel.domain.hospede.Hospede;
import com.desafio.hotel.domain.hospede.HospedeDTO;
import com.desafio.hotel.domain.hospede.converters.HospedeDTOToHospedeConverter;
import com.desafio.hotel.domain.hospede.converters.HospedeToHospedeDTOConverter;
import com.desafio.hotel.exceptions.HospedeNotFoundException;
import com.desafio.hotel.repository.HospedeRepository;
import com.desafio.hotel.service.HospedeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.support.FormattingConversionService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class HospedeServiceUnitTest {

    @Mock
    private HospedeRepository repository;

    private FormattingConversionService formattingConversionService;

    private HospedeService hospedeService;

    private HospedeDTO hospedeDTO;

    private Hospede hospede;

    @Captor
    private ArgumentCaptor<Hospede> hospedeCaptor;

    @BeforeEach
    void setUp() {
        formattingConversionService = new FormattingConversionService();
        formattingConversionService.addConverter(new HospedeDTOToHospedeConverter());
        formattingConversionService.addConverter(new HospedeToHospedeDTOConverter());
        hospedeService = new HospedeService(repository, formattingConversionService);
        hospedeDTO = HospedeTestHelper.createHospedeDTO();
        hospede = HospedeTestHelper.createHospede();
    }

    @Test
    void shouldCreateHospede() {
        hospedeService.create(hospedeDTO);

        Mockito.verify(repository , Mockito.atLeastOnce()).save(hospedeCaptor.capture());
        Assertions.assertEquals(hospedeDTO.getNome(), hospedeCaptor.getValue().getNome());
        Assertions.assertEquals(hospedeDTO.getDocumento(), hospedeCaptor.getValue().getDocumento());
        Assertions.assertEquals(hospedeDTO.getTelefone(), hospedeCaptor.getValue().getTelefone());
    }

    @Test
    void shouldUpdateHospede() {
        hospede.setId(UUID.randomUUID());
        hospedeDTO.setId(hospede.getId());
        hospedeDTO.setNome("Siclano de Tal");
        hospedeDTO.setDocumento("12345600");

        Mockito.when(repository.findById(hospedeDTO.getId())).thenReturn(Optional.ofNullable(hospede));

        hospedeService.update(hospedeDTO);

        Mockito.verify(repository, Mockito.atLeastOnce()).findById(hospedeDTO.getId());
        Mockito.verify(repository, Mockito.atLeastOnce()).save(hospedeCaptor.capture());
        Assertions.assertEquals(hospedeDTO.getNome(), hospedeCaptor.getValue().getNome());
        Assertions.assertEquals(hospedeDTO.getDocumento(), hospedeCaptor.getValue().getDocumento());
        Assertions.assertEquals(hospedeDTO.getTelefone(), hospedeCaptor.getValue().getTelefone());
    }

    @Test
    void shouldDeleteHospede() {
        hospede.setId(UUID.randomUUID());
        Mockito.when(repository.existsById(hospede.getId())).thenReturn(true);

        hospedeService.delete(hospede.getId());

        Mockito.verify(repository, Mockito.atLeastOnce()).existsById(hospede.getId());
        Mockito.verify(repository, Mockito.atLeastOnce()).deleteById(hospede.getId());
    }

    @Test
    void shouldFindHospede() {
        UUID hospedeId = UUID.randomUUID();
        Mockito.when(repository.findById(hospedeId)).thenReturn(Optional.ofNullable(hospede));
        HospedeDTO hospedeResult = hospedeService.findById(hospedeId);

        Mockito.verify(repository, Mockito.atLeastOnce()).findById(hospedeId);
        Mockito.verify(repository, Mockito.atLeastOnce()).findById(hospedeId);
        Assertions.assertEquals(hospede.getId(), hospedeResult.getId());
        Assertions.assertEquals(hospede.getNome(), hospedeResult.getNome());
        Assertions.assertEquals(hospede.getDocumento(), hospedeResult.getDocumento());
        Assertions.assertEquals(hospede.getTelefone(), hospedeResult.getTelefone());
    }

    @Test
    void shouldFindAllHospedes() {
        Hospede otherHospede = HospedeTestHelper.createHospede();
        otherHospede.setId(UUID.randomUUID());
        otherHospede.setNome("Siclano de Tal");
        otherHospede.setDocumento("12345600");
        otherHospede.setTelefone("16325");
        PageRequest pageRequest = PageRequest.of(0, 10);
        Mockito.when(repository.findAll(pageRequest)).thenReturn(new PageImpl<>(List.of(hospede, otherHospede)));

        Page<HospedeDTO> page = hospedeService.findAll(pageRequest);

        Mockito.verify(repository, Mockito.atLeastOnce()).findAll(pageRequest);
        Assertions.assertEquals(hospede.getId(), page.getContent().get(0).getId());
        Assertions.assertEquals(hospede.getNome(), page.getContent().get(0).getNome());
        Assertions.assertEquals(hospede.getDocumento(), page.getContent().get(0).getDocumento());
        Assertions.assertEquals(hospede.getTelefone(), page.getContent().get(0).getTelefone());
        Assertions.assertEquals(otherHospede.getId(), page.getContent().get(1).getId());
        Assertions.assertEquals(otherHospede.getNome(), page.getContent().get(1).getNome());
        Assertions.assertEquals(otherHospede.getDocumento(), page.getContent().get(1).getDocumento());
        Assertions.assertEquals(otherHospede.getTelefone(), page.getContent().get(1).getTelefone());
    }

    @Test
    void shouldFindAllWithCurrentCheckIn() {
        Hospede otherHospede = HospedeTestHelper.createHospede();
        otherHospede.setId(UUID.randomUUID());
        otherHospede.setNome("Siclano de Tal");
        otherHospede.setDocumento("12345600");
        otherHospede.setTelefone("16325");
        PageRequest pageRequest = PageRequest.of(0, 10);
        Mockito.when(repository.findAllWithCurrentCheckIn(pageRequest)).thenReturn(new PageImpl<>(List.of(hospede, otherHospede)));

        Page<HospedeDTO> page = hospedeService.findAllWithCurrentCheckIn(pageRequest);

        Mockito.verify(repository, Mockito.atLeastOnce()).findAllWithCurrentCheckIn(pageRequest);
        Assertions.assertEquals(hospede.getId(), page.getContent().get(0).getId());
        Assertions.assertEquals(hospede.getNome(), page.getContent().get(0).getNome());
        Assertions.assertEquals(hospede.getDocumento(), page.getContent().get(0).getDocumento());
        Assertions.assertEquals(hospede.getTelefone(), page.getContent().get(0).getTelefone());
        Assertions.assertEquals(otherHospede.getId(), page.getContent().get(1).getId());
        Assertions.assertEquals(otherHospede.getNome(), page.getContent().get(1).getNome());
        Assertions.assertEquals(otherHospede.getDocumento(), page.getContent().get(1).getDocumento());
        Assertions.assertEquals(otherHospede.getTelefone(), page.getContent().get(1).getTelefone());
    }

    @Test
    void shouldFindAllByCheckInExpered() {
        Hospede otherHospede = HospedeTestHelper.createHospede();
        otherHospede.setId(UUID.randomUUID());
        otherHospede.setNome("Siclano de Tal");
        otherHospede.setDocumento("12345600");
        otherHospede.setTelefone("16325");
        PageRequest pageRequest = PageRequest.of(0, 10);
        Mockito.when(repository.findAllByCheckInsExpired(pageRequest)).thenReturn(new PageImpl<>(List.of(hospede, otherHospede)));

        Page<HospedeDTO> page = hospedeService.findAllByCheckInExpered(pageRequest);

        Mockito.verify(repository, Mockito.atLeastOnce()).findAllByCheckInsExpired(pageRequest);
        Assertions.assertEquals(hospede.getId(), page.getContent().get(0).getId());
        Assertions.assertEquals(hospede.getNome(), page.getContent().get(0).getNome());
        Assertions.assertEquals(hospede.getDocumento(), page.getContent().get(0).getDocumento());
        Assertions.assertEquals(hospede.getTelefone(), page.getContent().get(0).getTelefone());
        Assertions.assertEquals(otherHospede.getId(), page.getContent().get(1).getId());
        Assertions.assertEquals(otherHospede.getNome(), page.getContent().get(1).getNome());
        Assertions.assertEquals(otherHospede.getDocumento(), page.getContent().get(1).getDocumento());
        Assertions.assertEquals(otherHospede.getTelefone(), page.getContent().get(1).getTelefone());
    }

    @Test
    void shouldNotDeleteHospedeWhenHospedeNotFound() {
        hospede.setId(UUID.randomUUID());
        Mockito.when(repository.existsById(hospede.getId())).thenReturn(false);

        Assertions.assertThrows(HospedeNotFoundException.class, () ->
                hospedeService.delete(hospede.getId()));

        Mockito.verify(repository, Mockito.atLeastOnce()).existsById(hospede.getId());
    }

    @Test
    void shouldNotFindWhenHospedeNotFound() {
        UUID hospedeId = UUID.randomUUID();

        Assertions.assertThrows(HospedeNotFoundException.class, () ->
                hospedeService.findById(hospedeId));

        Mockito.verify(repository, Mockito.atLeastOnce()).findById(hospedeId);
    }

    @Test
    void shouldNotUpdateWhenHospedeNotFound() {
        hospede.setId(UUID.randomUUID());

        Assertions.assertThrows(HospedeNotFoundException.class, () ->
                hospedeService.update(hospedeDTO));

        Mockito.verify(repository, Mockito.atLeastOnce()).findById(hospedeDTO.getId());
    }
}
