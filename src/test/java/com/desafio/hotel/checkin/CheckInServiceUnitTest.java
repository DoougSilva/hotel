package com.desafio.hotel.checkin;

import com.desafio.hotel.domain.checkin.CheckIn;
import com.desafio.hotel.domain.checkin.CheckInDTO;
import com.desafio.hotel.domain.checkin.converters.CheckInDTOToCheckInConverter;
import com.desafio.hotel.domain.hospede.Hospede;
import com.desafio.hotel.domain.hospede.HospedeDTO;
import com.desafio.hotel.exceptions.HospedeDuplicateResultException;
import com.desafio.hotel.hospede.HospedeTestHelper;
import com.desafio.hotel.repository.CheckInRepository;
import com.desafio.hotel.service.CheckInService;
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
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.format.support.FormattingConversionService;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class CheckInServiceUnitTest {

    @Mock
    private CheckInRepository repository;

    @Mock
    private HospedeService hospedeService;

    @Captor
    private ArgumentCaptor<CheckIn> checkInCaptor;

    private FormattingConversionService formattingConversionService;

    private CheckInService checkInService;

    private CheckInDTO checkInDTO;

    @BeforeEach
    void setUp() {
        formattingConversionService = new FormattingConversionService();
        formattingConversionService.addConverter(new CheckInDTOToCheckInConverter());
        checkInService = new CheckInService(repository, hospedeService, formattingConversionService);
        checkInDTO = CheckInTestHelper.createCheckInDTO(HospedeTestHelper.createHospedeDTO());
    }

    @Test
    void shouldCreateCheckIn() {
        Hospede hospede = HospedeTestHelper.createHospede();
        hospede.setId(UUID.randomUUID());

        Mockito.when(hospedeService.findByFields(checkInDTO.getHospede())).thenReturn(hospede);

        checkInService.create(checkInDTO);

        Mockito.verify(repository , Mockito.atLeastOnce()).save(checkInCaptor.capture());
        Mockito.verify(hospedeService , Mockito.atLeastOnce()).findByFields(checkInDTO.getHospede());
        Assertions.assertEquals(hospede, checkInCaptor.getValue().getHospede());
        Assertions.assertEquals(checkInDTO.getDataEntrada(), checkInCaptor.getValue().getDataEntrada());
        Assertions.assertEquals(checkInDTO.getDataSaida(), checkInCaptor.getValue().getDataSaida());
        Assertions.assertNotNull(checkInCaptor.getValue().getValor());
    }

    @Test
    void shouldThrowExceptionWhenHospedeIsDuplicate() {
        Hospede hospede = HospedeTestHelper.createHospede();
        hospede.setId(UUID.randomUUID());

        Mockito.when(hospedeService.findByFields(checkInDTO.getHospede())).thenThrow(IncorrectResultSizeDataAccessException.class);

        Assertions.assertThrows(HospedeDuplicateResultException.class, () ->
                checkInService.create(checkInDTO));
        Mockito.verify(hospedeService, Mockito.atLeastOnce()).findByFields(checkInDTO.getHospede());
        Mockito.verify(repository, Mockito.never()).save(Mockito.any(CheckIn.class));
    }
}
