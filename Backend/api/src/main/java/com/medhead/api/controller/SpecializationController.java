package com.medhead.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medhead.api.model.Specialization;
import com.medhead.api.service.SpecializationService;

@RestController
public class SpecializationController {
    @Autowired
    private SpecializationService specializationService;

    @GetMapping("/specializations")
    public Iterable<Specialization> getSpecializations() {
        return specializationService.getSpecializations();
    }
    
    @PostMapping("/specialization")
    public Specialization saveSpecialization(@RequestBody Specialization specialization) {
        return specializationService.saveSpecialization(specialization);
    }

    @GetMapping("/specialization/{id}")
    public Optional<Specialization> getSpecialization(@PathVariable int id) {
        return specializationService.getSpecialization(id);
    }

    @PutMapping("/specialization")
    public Specialization updateSpecialization(@RequestBody Specialization specialization) {
        return specializationService.saveSpecialization(specialization);
    }

    @DeleteMapping("/specialization/{id}")
    public void deleteSpecialization(@PathVariable int id) {
        specializationService.deleteSpecialization(id);
    }
}
