package com.medhead.api.controller;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medhead.api.model.Hospital;
import com.medhead.api.model.Specialization;
import com.medhead.api.service.HospitalService;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @GetMapping("/hospitals")
    public Iterable<Hospital> getHospitals() {
        return hospitalService.getHospitals();
    }

    @PostMapping("/hospital")
    public Hospital saveHospital(@RequestBody Hospital hospital) {
        return hospitalService.saveHospital(hospital);
    }

    @GetMapping("/hospital/{id}")
    public Optional<Hospital> getHospital(@PathVariable int id) {
        return hospitalService.getHospital(id);
    }

    @PutMapping("/put/hospital")
    public Hospital updateHospital(@RequestBody Hospital hospital) {
        return hospitalService.saveHospital(hospital);
    }

    @DeleteMapping("/hospital/{id}")
    public void deleteHospital(@PathVariable int id) {
        hospitalService.deleteHospital(id);
    }

    @GetMapping("/hospital/{id}/specializations")
    public Set<Specialization> getHospitalSpecializations(@PathVariable int id) {
        return hospitalService.getHospitalSpecializations(id);
    }

    @GetMapping("/distance/{hospitalSource}/{hospitalDest}")
    public String getDistance(@PathVariable int hospitalSource, @PathVariable int hospitalDest) {
        // Get the source and destination 
        Hospital source = hospitalService.getHospital(hospitalSource)
                .orElseThrow(() -> new RuntimeException("Source hospital not found"));
        Hospital dest = hospitalService.getHospital(hospitalDest)
                .orElseThrow(() -> new RuntimeException("Destination hospital not found"));
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://trueway-matrix.p.rapidapi.com/CalculateDrivingMatrix?origins=" + source.getGps() +
                        "&destinations=" + dest.getGps())
                .get()
                .addHeader("X-RapidAPI-Key", "a603ae1540msh136f140d42b40abp1760acjsn0b679ee97d41")
                .addHeader("X-RapidAPI-Host", "trueway-matrix.p.rapidapi.com")
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            // Handle the exception
            throw new RuntimeException("Error calling TrueWay Matrix API", e);
        }
    }

}
