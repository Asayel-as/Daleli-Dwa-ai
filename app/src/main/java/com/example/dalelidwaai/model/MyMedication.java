package com.example.dalelidwaai.model;

import java.util.List;
import java.util.UUID;

public class MyMedication {

    String id;
    Long expiryDate;
    Medication medication;
    List<Dosage> dosages;

    public void setExpiryDate(Long expiryDate) {
        this.expiryDate = expiryDate;
    }

    public MyMedication(Long expiryDate, Medication medication, List<Dosage> dosages) {
        id = UUID.randomUUID().toString();
        this.expiryDate = expiryDate;
        this.medication = medication;
        this.dosages = dosages;
    }

    public String getId() {
        return id;
    }

    public Long getExpiryDate() {
        return expiryDate;
    }

    public Medication getMedication() {
        return medication;
    }

    public List<Dosage> getDosages() {
        return dosages;
    }
}
