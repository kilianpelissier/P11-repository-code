package com.medhead.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.medhead.api.model.Specialization;

@Repository
public interface SpecializationRepository extends CrudRepository<Specialization, Integer>{
}
