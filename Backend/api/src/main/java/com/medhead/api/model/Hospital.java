package com.medhead.api.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@Table(name = "HOSPITAL")
public class Hospital {    
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

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "SPECIALIZATION_HOSPITAL",
        joinColumns = @JoinColumn(name = "ID_HOSPITAL"),
        inverseJoinColumns = @JoinColumn(name = "ID_SPECIALIZATION"))
        @EqualsAndHashCode.Exclude
        @JsonManagedReference
        private Set<Specialization> specializations;
        
        // public Hospital(String name, String address, String gps, int numberBed, int numberFreeBed) {
        //     this.name = name;
        //     this.address = address;
        //     this.gps = gps;
        //     this.numberBed = numberBed;
        //     this.numberFreeBed = numberFreeBed;
        // }
    
}
