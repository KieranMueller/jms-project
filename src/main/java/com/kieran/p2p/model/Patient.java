package com.kieran.p2p.model;

import java.io.Serial;
import java.io.Serializable;

public class Patient implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String insuranceProvider;
    private Double copay;
    private Double amountToPay;

    public Patient() {}

    public Patient(Long id, String name, String insuranceProvider, Double copay, Double amountToPay) {
        this.id = id;
        this.name = name;
        this.insuranceProvider = insuranceProvider;
        this.copay = copay;
        this.amountToPay = amountToPay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInsuranceProvider() {
        return insuranceProvider;
    }

    public void setInsuranceProvider(String insuranceProvider) {
        this.insuranceProvider = insuranceProvider;
    }

    public Double getCopay() {
        return copay;
    }

    public void setCopay(Double copay) {
        this.copay = copay;
    }

    public Double getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(Double amountToPay) {
        this.amountToPay = amountToPay;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", insuranceProvider='" + insuranceProvider + '\'' +
                ", copay=" + copay +
                ", amountToPay=" + amountToPay +
                '}';
    }
}
