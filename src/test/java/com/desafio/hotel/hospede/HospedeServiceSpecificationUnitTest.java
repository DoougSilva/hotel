package com.desafio.hotel.hospede;

import com.desafio.hotel.domain.hospede.Hospede;
import com.desafio.hotel.domain.hospede.HospedeDTO;
import com.desafio.hotel.exceptions.ValidateException;
import com.desafio.hotel.repository.HospedeRepository;
import com.desafio.hotel.service.HospedeServiceSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HospedeServiceSpecificationUnitTest {

    @Mock
    private HospedeRepository hospedeRepository;

    private HospedeDTO hospedeDTO;

    private Hospede hospede;

    @BeforeEach
    void setUp() {
        hospedeDTO = HospedeTestHelper.createHospedeDTO();
        hospede = HospedeTestHelper.createHospede();
        hospede.setTelefone("0000");
        hospede.setDocumento("1111");
    }

    @Test
    void shouldReturnTrueWhenTelefoneNotExists() {
        Mockito.when(hospedeRepository.existsByTelefone(hospedeDTO.getTelefone())).thenReturn(false);

        Assertions.assertTrue(HospedeServiceSpecification.validateTelefone(hospedeRepository).isSatisfiedBy(hospedeDTO));
    }

    @Test
    void shouldReturnFalseWhenTelefoneExists() {
        Mockito.when(hospedeRepository.existsByTelefone(hospedeDTO.getTelefone())).thenReturn(true);

        Assertions.assertFalse(HospedeServiceSpecification.validateTelefone(hospedeRepository).isSatisfiedBy(hospedeDTO));
    }

    @Test
    void shouldThrowExceptionWhenTelefoneExists() {
        Mockito.when(hospedeRepository.existsByTelefone(hospedeDTO.getTelefone())).thenReturn(true);

        Assertions.assertThrows(ValidateException.class, () ->
                HospedeServiceSpecification.validateTelefone(hospedeRepository).isSatisfiedByWithException(hospedeDTO));
    }

    @Test
    void shouldReturnTrueWhenDocumentoNotExists() {
        Mockito.when(hospedeRepository.existsByDocumento(hospedeDTO.getDocumento())).thenReturn(false);

        Assertions.assertTrue(HospedeServiceSpecification.validateDocumento(hospedeRepository).isSatisfiedBy(hospedeDTO));
    }

    @Test
    void shouldReturnFalseWhenDocumentoExists() {
        Mockito.when(hospedeRepository.existsByDocumento(hospedeDTO.getDocumento())).thenReturn(true);

        Assertions.assertFalse(HospedeServiceSpecification.validateDocumento(hospedeRepository).isSatisfiedBy(hospedeDTO));
    }

    @Test
    void shouldThrowExceptionWhenDocumentoExists() {
        Mockito.when(hospedeRepository.existsByDocumento(hospedeDTO.getDocumento())).thenReturn(true);

        Assertions.assertThrows(ValidateException.class, () ->
                HospedeServiceSpecification.validateDocumento(hospedeRepository).isSatisfiedByWithException(hospedeDTO));
    }

    @Test
    void shouldReturnTrueWhenTelefoneUpdateNotExists() {
        Mockito.when(hospedeRepository.existsByTelefone(hospedeDTO.getTelefone())).thenReturn(false);

        Assertions.assertTrue(HospedeServiceSpecification.validateTelefoneUpdate(hospedeRepository, hospede).isSatisfiedBy(hospedeDTO));
    }
    @Test
    void shouldReturnTrueWhenTelefoneUpdateIsEquals() {
        hospede.setTelefone(hospedeDTO.getTelefone());

        Assertions.assertTrue(HospedeServiceSpecification.validateTelefoneUpdate(hospedeRepository, hospede).isSatisfiedBy(hospedeDTO));
    }

    @Test
    void shouldReturnFalseWhenTelefoneUpdateExists() {
        Mockito.when(hospedeRepository.existsByTelefone(hospedeDTO.getTelefone())).thenReturn(true);

        Assertions.assertFalse(HospedeServiceSpecification.validateTelefoneUpdate(hospedeRepository, hospede).isSatisfiedBy(hospedeDTO));
    }

    @Test
    void shouldThrowExceptionWhenTelefoneUpdateExists() {
        Mockito.when(hospedeRepository.existsByTelefone(hospedeDTO.getTelefone())).thenReturn(true);

        Assertions.assertThrows(ValidateException.class, () ->
                HospedeServiceSpecification.validateTelefoneUpdate(hospedeRepository, hospede).isSatisfiedByWithException(hospedeDTO));
    }

    @Test
    void shouldReturnTrueWhenDocumentoUpdateNotExists() {
        Mockito.when(hospedeRepository.existsByDocumento(hospedeDTO.getDocumento())).thenReturn(false);

        Assertions.assertTrue(HospedeServiceSpecification.validateDocumentoUpdate(hospedeRepository, hospede).isSatisfiedBy(hospedeDTO));
    }

    @Test
    void shouldReturnTrueWhenDocumentoUpdateIsEquals() {
        hospede.setDocumento(hospedeDTO.getDocumento());

        Assertions.assertTrue(HospedeServiceSpecification.validateDocumentoUpdate(hospedeRepository, hospede).isSatisfiedBy(hospedeDTO));
    }

    @Test
    void shouldReturnFalseWhenDocumentoUpdateExists() {
        Mockito.when(hospedeRepository.existsByDocumento(hospedeDTO.getDocumento())).thenReturn(true);

        Assertions.assertFalse(HospedeServiceSpecification.validateDocumentoUpdate(hospedeRepository, hospede).isSatisfiedBy(hospedeDTO));
    }

    @Test
    void shouldThrowExceptionWhenDocumentoUpdateExists() {
        Mockito.when(hospedeRepository.existsByDocumento(hospedeDTO.getDocumento())).thenReturn(true);

        Assertions.assertThrows(ValidateException.class, () ->
                HospedeServiceSpecification.validateDocumentoUpdate(hospedeRepository, hospede).isSatisfiedByWithException(hospedeDTO));
    }
}
