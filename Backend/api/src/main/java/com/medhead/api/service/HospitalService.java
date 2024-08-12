package com.medhead.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medhead.api.exception.ResourceNotFoundException;
import com.medhead.api.model.Hospital;
import com.medhead.api.model.Specialization;
import com.medhead.api.repository.HospitalRepository;
import com.medhead.api.repository.SpecializationRepository;
import java.util.Set;

import lombok.Data;

@Data
@Service("hospitalService")
public class HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private SpecializationRepository specializationRepository; // Ajouté

    public Iterable<Hospital> getHospitals() {
        return hospitalRepository.findAll();
    }

    public Optional<Hospital> getHospital(int id) {
        return hospitalRepository.findById(id);
    }

    public Hospital saveHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }
    // delete function, return 1 if success, 0 if failed
    public boolean deleteHospital(int id) {
        try {
            hospitalRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public Set<Specialization> getHospitalSpecializations(int id) {
        Optional<Hospital> hospital = hospitalRepository.findById(id);
        if (hospital.isPresent()) {
            return hospital.get().getSpecializations();
        } else {
            throw new ResourceNotFoundException("Hospital", "id", id);
        }
    }

    public Set<Hospital> getHospitalsBySpecialization(Integer specializationId) {
        Specialization specialization = specializationRepository.findById(specializationId)
        .orElseThrow(() -> new RuntimeException("Specialization not found"));
        return specialization.getHospitals();
    }
}