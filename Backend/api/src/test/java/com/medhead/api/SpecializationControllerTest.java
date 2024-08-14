package com.medhead.api;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medhead.api.model.Specialization;
import com.medhead.api.service.SpecializationService;

@SpringBootTest
@AutoConfigureMockMvc
public class SpecializationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpecializationService specializationService;

    private static final String API_KEY_HEADER = "X-API-KEY";
    private static final String API_KEY = "203f16bd-d289-452c-9371-b0e042a64ef4";

    @Test
    public void testGetSpecializations() throws Exception {
        Specialization specialization = new Specialization();
        specialization.setId(1);
        specialization.setLibelle("Test Specialization");
        specialization.setLibgroup("Test Description");

        given(specializationService.getSpecializations()).willReturn(Arrays.asList(specialization));

        mockMvc.perform(get("/specializations")
                .header(API_KEY_HEADER, API_KEY))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)));
    }

    @Test
    public void testSaveSpecialization() throws Exception {
        Specialization specialization = new Specialization();
        specialization.setLibelle("Test Specialization");
        specialization.setLibgroup("Test Description");

        given(specializationService.saveSpecialization(any(Specialization.class))).willReturn(specialization);

        ObjectMapper objectMapper = new ObjectMapper();
        String specializationJson = objectMapper.writeValueAsString(specialization);

        mockMvc.perform(post("/saveSpecialization")
                .header(API_KEY_HEADER, API_KEY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(specializationJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.libelle", is(specialization.getLibelle())));
    }

    @Test
    public void testUpdateSpecialization() throws Exception {
        Specialization specialization = new Specialization();
        specialization.setId(1);
        specialization.setLibelle("Updated Test Specialization");

        given(specializationService.saveSpecialization(any(Specialization.class))).willReturn(specialization);

        mockMvc.perform(put("/put/specialization")
                .header(API_KEY_HEADER, API_KEY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(specialization)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.libelle", is(specialization.getLibelle())));
    }

    @Test
    public void testDeleteSpecialization() throws Exception {
        given(specializationService.deleteSpecialization(anyInt())).willReturn(true);

        mockMvc.perform(delete("/specialization/1")
                .header(API_KEY_HEADER, API_KEY))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetSpecialization() throws Exception {
        Specialization specialization = new Specialization();
        specialization.setId(2);
        specialization.setLibelle("Test Specialization");

        given(specializationService.getSpecialization(2)).willReturn(Optional.of(specialization));

        mockMvc.perform(get("/specialization/2")
                .header(API_KEY_HEADER, API_KEY))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)));
    }
}