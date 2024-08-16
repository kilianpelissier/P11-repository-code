package com.medhead.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.medhead.api.model.Specialization;
import com.medhead.api.repository.SpecializationRepository;
import com.medhead.api.service.SpecializationService;

public class SpecializationServiceTest {

    @InjectMocks
    private SpecializationService specializationService;

    @Mock
    private SpecializationRepository specializationRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetSpecializations() {
        Iterable<Specialization> specializations = new HashSet<>();
        when(specializationRepository.findAll()).thenReturn(specializations);

        Iterable<Specialization> result = specializationService.getSpecializations();
        assertEquals(specializations, result);
    }

    @Test
    public void testGetSpecialization() {
        Specialization specialization = new Specialization();
        when(specializationRepository.findById(1)).thenReturn(Optional.of(specialization));

        Optional<Specialization> result = specializationService.getSpecialization(1);
        assertTrue(result.isPresent());
        assertEquals(specialization, result.get());
    }

    @Test
    public void testSaveSpecialization() {
        Specialization specialization = new Specialization();
        when(specializationRepository.save(specialization)).thenReturn(specialization);

        Specialization result = specializationService.saveSpecialization(specialization);
        assertEquals(specialization, result);
    }

    @Test
    public void testDeleteSpecialization() {
        doNothing().when(specializationRepository).deleteById(1);

        boolean result = specializationService.deleteSpecialization(1);
        assertTrue(result);
    }

    @Test
    public void testDeleteSpecializationFailure() {
        doThrow(new RuntimeException()).when(specializationRepository).deleteById(1);

        boolean result = specializationService.deleteSpecialization(1);
        assertFalse(result);
    }
}