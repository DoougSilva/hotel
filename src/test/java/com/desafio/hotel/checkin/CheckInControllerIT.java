package com.desafio.hotel.checkin;

import com.desafio.hotel.controller.CheckInController;
import com.desafio.hotel.domain.checkin.CheckInDTO;
import com.desafio.hotel.domain.hospede.Hospede;
import com.desafio.hotel.domain.hospede.HospedeDTO;
import com.desafio.hotel.hospede.HospedeTestHelper;
import com.desafio.hotel.repository.CheckInRepository;
import com.desafio.hotel.repository.HospedeRepository;
import com.desafio.hotel.service.CheckInService;
import com.desafio.hotel.utils.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
public class CheckInControllerIT {

    @Autowired
    private CheckInService checkInService;

    @Autowired
    private CheckInRepository checkInRepository;

    @Autowired
    private HospedeRepository hospedeRepository;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.standaloneSetup(new CheckInController(checkInService)).build();
    }

    @AfterEach
    void tearDown() {
        checkInRepository.deleteAll();
        hospedeRepository.deleteAll();
    }

    @Test
    void testCreate() throws Exception {
        Hospede hospede = HospedeTestHelper.createHospede();
        hospede = hospedeRepository.saveAndFlush(hospede);
        CheckInDTO checkInDTO = CheckInTestHelper.createCheckInDTO(HospedeDTO.builder().nome(hospede.getNome()).build());

        mockMvc.perform(post("/check-in")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.convertBytes(checkInDTO))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }
}
