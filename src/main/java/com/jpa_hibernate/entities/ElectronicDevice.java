package com.jpa_hibernate.entities;

import jakarta.persistence.Entity;

@Entity
public class ElectronicDevice extends ProductInheritance {

    private int voltage;

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }
}
