package com.medhead.api.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Set;

import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties({"hospitals"})
@Table(name = "SPECIALIZATION")
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "libgroup")
    private String libgroup;

    @ManyToMany(mappedBy = "specializations")
    @JsonBackReference
    private Set<Hospital> hospitals;

    // public Specialization(String libelle, String libgroup) {
    //     this.libelle = libelle;
    //     this.libgroup = libgroup;
    // }
}
