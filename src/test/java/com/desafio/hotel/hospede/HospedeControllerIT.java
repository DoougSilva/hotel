package com.desafio.hotel.hospede;

import com.desafio.hotel.checkin.CheckInTestHelper;
import com.desafio.hotel.controller.HospedeController;
import com.desafio.hotel.domain.checkin.CheckIn;
import com.desafio.hotel.domain.hospede.Hospede;
import com.desafio.hotel.domain.hospede.HospedeDTO;
import com.desafio.hotel.repository.CheckInRepository;
import com.desafio.hotel.repository.HospedeRepository;
import com.desafio.hotel.service.HospedeService;
import com.desafio.hotel.utils.TestUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
public class HospedeControllerIT {

    @Autowired
    private HospedeService hospedeService;

    @Autowired
    private HospedeRepository hospedeRepository;

    @Autowired
    private CheckInRepository checkInRepository;

    private MockMvc mockMvc;

    private HospedeDTO hospedeDTO;

    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.standaloneSetup(new HospedeController(hospedeService)).build();
        hospedeDTO = HospedeTestHelper.createHospedeDTO();
    }

    @AfterEach
    void tearDown() {
        hospedeRepository.deleteAll();
        checkInRepository.deleteAll();
    }

    private String performCreate(HospedeDTO dto) throws Exception {
        MvcResult result = mockMvc.perform(post("/hospede")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(TestUtils.convertBytes(dto))
                            .accept(MediaType.APPLICATION_JSON_VALUE))
                            .andExpect(status().isCreated())
                            .andReturn();

        JSONObject json = new JSONObject(result.getResponse().getContentAsString());

        return json.get("id").toString();
    }

    @Test
    void testCreate() throws Exception {
        performCreate(hospedeDTO);
    }

    @Test
    void testUpdate() throws Exception {
        String uuid = performCreate(hospedeDTO);
        hospedeDTO.setId(UUID.fromString(uuid));
        hospedeDTO.setNome("Siclano de Tal");
        hospedeDTO.setDocumento("123456");

        mockMvc.perform(put("/hospede")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.convertBytes(hospedeDTO))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        Hospede entityUpdated = hospedeRepository.findById(UUID.fromString(uuid)).get();

        Assertions.assertEquals(hospedeDTO.getId(), entityUpdated.getId());
        Assertions.assertEquals(hospedeDTO.getNome(), entityUpdated.getNome());
        Assertions.assertEquals(hospedeDTO.getDocumento(), entityUpdated.getDocumento());
        Assertions.assertEquals(hospedeDTO.getTelefone(), entityUpdated.getTelefone());
    }

    @Test
    void testFindById() throws Exception {
        String uuid = performCreate(hospedeDTO);

        mockMvc.perform(get(String.format("/hospede/%s", uuid))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.convertBytes(hospedeDTO))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void testFindAll() throws Exception {
        performCreate(hospedeDTO);
        hospedeDTO.setNome("Siclano de Tal");
        hospedeDTO.setDocumento("123456");
        hospedeDTO.setTelefone("33558877");

        mockMvc.perform(get("/hospede/pageable")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.convertBytes(hospedeDTO))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void findAllByCheckInExpered() throws Exception {
        Hospede hospede = HospedeTestHelper.createHospede();
        hospede = hospedeRepository.saveAndFlush(hospede);
        CheckIn checkIn = CheckInTestHelper.createCheckIn(hospede);
        checkIn.setDataEntrada(LocalDateTime.now().minusDays(2));
        checkIn.setDataSaida(LocalDateTime.now().minusDays(1));
        checkIn.setValor(150.00);
        checkInRepository.saveAndFlush(checkIn);

        mockMvc.perform(get("/hospede/pageable")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.convertBytes(hospedeDTO))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id", is(hospede.getId().toString())));

    }

    @Test
    void findAllWithCurrentCheckIn() throws Exception {
        Hospede hospede = HospedeTestHelper.createHospede();
        hospede = hospedeRepository.saveAndFlush(hospede);
        CheckIn checkIn = CheckInTestHelper.createCheckIn(hospede);
        checkIn.setDataEntrada(LocalDateTime.now().minusDays(1));
        checkIn.setDataSaida(LocalDateTime.now().plusDays(1));
        checkIn.setValor(120.00);
        checkInRepository.saveAndFlush(checkIn);

        mockMvc.perform(get("/hospede/pageable")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.convertBytes(hospedeDTO))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id", is(hospede.getId().toString())));

    }

    @Test
    void testDelete() throws Exception {
        String uuid = performCreate(hospedeDTO);

        mockMvc.perform(delete(String.format("/hospede/%s", uuid))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.convertBytes(hospedeDTO))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent())
                .andReturn();
    }
}
