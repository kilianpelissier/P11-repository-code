package com.medhead.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medhead.api.model.Specialization;



@SpringBootTest
@AutoConfigureMockMvc
public class SpecializationControllerTest {
    @Autowired
    private MockMvc mockMvc;
// test for specialization entity
@Test
public void testGetSpecializations() throws Exception {
    mockMvc.perform(get("/specializations"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id", is(1)));
}

@Test
public void testSaveSpecialization() throws Exception {
    // Créez un nouvel objet Specialization
    Specialization specialization = new Specialization();
    specialization.setLibelle("Test Specialization");
    specialization.setLibgroup("Test Description");
    ObjectMapper objectMapper = new ObjectMapper();
    String specializationJson = objectMapper.writeValueAsString(specialization);
    mockMvc.perform(post("/saveSpecialization")
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
    // set other properties of specialization

    mockMvc.perform(put("/put/specialization")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(specialization)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.libelle", is(specialization.getLibelle())));
}

@Test
public void testDeleteSpecialization() throws Exception {
    mockMvc.perform(delete("/specialization/1"))
            .andExpect(status().isOk());
}

@Test
public void testGetSpecialization() throws Exception {
    mockMvc.perform(get("/specialization/2"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(2)));
}
}
