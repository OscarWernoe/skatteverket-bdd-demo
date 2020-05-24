package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class IU {

    public Long getId() {
        return id;
    }

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    Long specifikationsnummer;
    String uppgiftslamnare;
    String inkomsttagare;
    String leveranspersonummer;
    String redovisningsperiod;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    Status status;

    public void setId(Long id) {
        this.id = id;
    }
}
