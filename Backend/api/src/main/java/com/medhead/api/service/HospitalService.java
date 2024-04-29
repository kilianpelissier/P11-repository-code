package com.medhead.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.medhead.api.model.Hospital;
import com.medhead.api.repository.HospitalRepository;
import com.medhead.api.exception.ResourceNotFoundException;
import com.medhead.api.model.Specialization;
import java.util.Set;

import lombok.Data;

@Data
@Service("hospitalService")
public class HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    public Iterable<Hospital> getHospitals() {
        return hospitalRepository.findAll();
    }

    public Optional<Hospital> getHospital(int id) {
        return hospitalRepository.findById(id);
    }

    public Hospital saveHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    public void deleteHospital(int id) {
        hospitalRepository.deleteById(id);
    }

    public Set<Specialization> getHospitalSpecializations(int id) {
        Optional<Hospital> hospital = hospitalRepository.findById(id);
        return hospital.get().getSpecializations();
        // if (hospital.isPresent()) {
        //     return hospital.get().getSpecializations();
        // } else {
        //     throw new ResourceNotFoundException("Hospital", "id", id);
        // }
    }
}
