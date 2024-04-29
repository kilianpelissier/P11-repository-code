package com.medhead.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.medhead.api.model.Hospital;

@Repository
public interface HospitalRepository extends CrudRepository<Hospital, Integer>{
    

}
