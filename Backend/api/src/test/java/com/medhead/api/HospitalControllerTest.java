package com.medhead.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medhead.api.model.Hospital;

@SpringBootTest
@AutoConfigureMockMvc
public class HospitalControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetHospitals() throws Exception {
        mockMvc.perform(get("/hospitals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(2)));
    }

    @Test
    public void testSaveHospital() throws Exception {
        // Créez un nouvel objet Hospital
        Hospital hospital = new Hospital();
        hospital.setName("Test Hospital");
        hospital.setAddress("123 Test Street");
        hospital.setGps("45.123, -75.123");
        hospital.setNumberBed(100);
        hospital.setNumberFreeBed(50);
        ObjectMapper objectMapper = new ObjectMapper();
        String hospitalJson = objectMapper.writeValueAsString(hospital);
        mockMvc.perform(post("/saveHospital")
                .contentType(MediaType.APPLICATION_JSON)
                .content(hospitalJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(hospital.getName())));
    }

    @Test
    public void testUpdateHospital() throws Exception {
        Hospital hospital = new Hospital();
        hospital.setId(1);
        hospital.setName("Updated Test Hospital");
        // set other properties of hospital

        mockMvc.perform(put("/put/hospital")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(hospital)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Updated Test Hospital")));
    }

    @Test
    public void testDeleteHospital() throws Exception {
        mockMvc.perform(delete("/hospital/1"))
                .andExpect(status().isOk());
    }

}
