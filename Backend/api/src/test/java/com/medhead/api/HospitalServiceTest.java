package com.medhead.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.medhead.api.exception.ResourceNotFoundException;
import com.medhead.api.model.Hospital;
import com.medhead.api.model.Specialization;
import com.medhead.api.repository.HospitalRepository;
import com.medhead.api.service.HospitalService;
import com.medhead.api.repository.SpecializationRepository;

public class HospitalServiceTest {

    @InjectMocks
    private HospitalService hospitalService;

    @Mock
    private HospitalRepository hospitalRepository;

    @Mock
    private SpecializationRepository specializationRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetHospitals() {
        Iterable<Hospital> hospitals = new HashSet<>();
        when(hospitalRepository.findAll()).thenReturn(hospitals);

        Iterable<Hospital> result = hospitalService.getHospitals();
        assertEquals(hospitals, result);
    }

    @Test
    public void testGetHospital() {
        Hospital hospital = new Hospital();
        when(hospitalRepository.findById(1)).thenReturn(Optional.of(hospital));

        Optional<Hospital> result = hospitalService.getHospital(1);
        assertTrue(result.isPresent());
        assertEquals(hospital, result.get());
    }

    @Test
    public void testSaveHospital() {
        Hospital hospital = new Hospital();
        when(hospitalRepository.save(hospital)).thenReturn(hospital);

        Hospital result = hospitalService.saveHospital(hospital);
        assertEquals(hospital, result);
    }

    @Test
    public void testDeleteHospital() {
        doNothing().when(hospitalRepository).deleteById(1);

        boolean result = hospitalService.deleteHospital(1);
        assertTrue(result);
    }

    @Test
    public void testDeleteHospitalFailure() {
        doThrow(new RuntimeException()).when(hospitalRepository).deleteById(1);

        boolean result = hospitalService.deleteHospital(1);
        assertFalse(result);
    }

    @Test
    public void testGetHospitalSpecializations() {
        Hospital hospital = new Hospital();
        Set<Specialization> specializations = new HashSet<>();
        hospital.setSpecializations(specializations);
        when(hospitalRepository.findById(1)).thenReturn(Optional.of(hospital));

        Set<Specialization> result = hospitalService.getHospitalSpecializations(1);
        assertEquals(specializations, result);
    }

    @Test
    public void testGetHospitalSpecializationsNotFound() {
        when(hospitalRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            hospitalService.getHospitalSpecializations(1);
        });
    }

    @Test
    public void testGetHospitalsBySpecialization() {
        Specialization specialization = new Specialization();
        Set<Hospital> hospitals = new HashSet<>();
        specialization.setHospitals(hospitals);
        when(specializationRepository.findById(1)).thenReturn(Optional.of(specialization));

        Set<Hospital> result = hospitalService.getHospitalsBySpecialization(1);
        assertEquals(hospitals, result);
    }

    @Test
    public void testGetHospitalsBySpecializationNotFound() {
        when(specializationRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            hospitalService.getHospitalsBySpecialization(1);
        });
    }
}