package com.medhead.api.service;

import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.medhead.api.model.Specialization;
import com.medhead.api.repository.SpecializationRepository;

import lombok.Data;

@Data
@Service("specializationService")
public class SpecializationService {
    @Autowired
    private SpecializationRepository specializationRepository;

    public Iterable<Specialization> getSpecializations() {
        return specializationRepository.findAll();
    }

    public Optional<Specialization> getSpecialization(int id) {
        return specializationRepository.findById(id);
    }

    public Specialization saveSpecialization(Specialization specialization) {
        return specializationRepository.save(specialization);
    }

    public void deleteSpecialization(int id) {
        specializationRepository.deleteById(id);
    }
}
