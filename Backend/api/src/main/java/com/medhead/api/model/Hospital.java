package com.medhead.api.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@JsonIgnoreProperties({"specializations"})
@Table(name = "HOSPITAL")
public class Hospital {
    // ...
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        private int id;
    
        @Column(name = "name")
        private String name;
    
        @Column(name = "address")
        private String address;
    
        @Column(name = "gps")
        private String gps;
    
        @Column(name = "numberBed")
        private int numberBed;
    
        @Column(name = "numberFreeBed")
        private int numberFreeBed;

        @ElementCollection(fetch = FetchType.EAGER)
        @ManyToMany
        @JoinTable(name = "SPECIALIZATION_HOSPITAL",
        joinColumns = @JoinColumn(name = "ID_HOSPITAL"),
        inverseJoinColumns = @JoinColumn(name = "ID_SPECIALIZATION"))
        @EqualsAndHashCode.Exclude
        @JsonManagedReference
        private Set<Specialization> specializations;   
}
