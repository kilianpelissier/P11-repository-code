package com.medhead.api;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medhead.api.model.Hospital;
import com.medhead.api.service.HospitalService;

@SpringBootTest
@AutoConfigureMockMvc
public class HospitalControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HospitalService hospitalService;

    private static final String API_KEY_HEADER = "X-API-KEY";
    private static final String API_KEY = "203f16bd-d289-452c-9371-b0e042a64ef4";

    @Test
    public void testGetHospitals() throws Exception {
        Hospital hospital = new Hospital();
        hospital.setId(2);
        hospital.setName("Test Hospital");
        hospital.setAddress("123 Test Street");
        hospital.setGps("45.123, -75.123");
        hospital.setNumberBed(100);
        hospital.setNumberFreeBed(50);

        given(hospitalService.getHospitals()).willReturn(Arrays.asList(hospital));

        mockMvc.perform(get("/hospitals")
                .header(API_KEY_HEADER, API_KEY))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(2)));
    }

    @Test
    public void testSaveHospital() throws Exception {
        Hospital hospital = new Hospital();
        hospital.setName("Test Hospital");
        hospital.setAddress("123 Test Street");
        hospital.setGps("45.123, -75.123");
        hospital.setNumberBed(100);
        hospital.setNumberFreeBed(50);

        given(hospitalService.saveHospital(any(Hospital.class))).willReturn(hospital);

        ObjectMapper objectMapper = new ObjectMapper();
        String hospitalJson = objectMapper.writeValueAsString(hospital);

        mockMvc.perform(post("/saveHospital")
                .header(API_KEY_HEADER, API_KEY)
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
        hospital.setAddress("123 Test Street");
        hospital.setGps("45.123, -75.123");
        hospital.setNumberBed(100);
        hospital.setNumberFreeBed(50);

        given(hospitalService.saveHospital(any(Hospital.class))).willReturn(hospital);

        ObjectMapper objectMapper = new ObjectMapper();
        String hospitalJson = objectMapper.writeValueAsString(hospital);

        mockMvc.perform(put("/put/hospital")
                .header(API_KEY_HEADER, API_KEY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(hospitalJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Updated Test Hospital")));
    }

    @Test
    public void testDeleteHospital() throws Exception {
        // Assurez-vous que deleteHospital retourne un boolean
        given(hospitalService.deleteHospital(anyInt())).willReturn(true);
    
        mockMvc.perform(delete("/hospital/10")
                .header(API_KEY_HEADER, API_KEY))
                .andExpect(status().isOk());
    }
}