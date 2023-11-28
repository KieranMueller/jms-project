package com.kieran.p2p.model;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

public class Transaction implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String cardHolder;
    private String phoneNumber;
    private String paidTo;
    private String location;
    private Timestamp time = new Timestamp(System.currentTimeMillis());
    private Double amount;

    public Transaction() {
    }

    public Transaction(String cardHolder, String phoneNumber, String paidTo, String location, Double amount) {
        this.cardHolder = cardHolder;
        this.phoneNumber = phoneNumber;
        this.paidTo = paidTo;
        this.location = location;
        this.amount = amount;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPaidTo() {
        return paidTo;
    }

    public void setPaidTo(String paidTo) {
        this.paidTo = paidTo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "cardHolder='" + cardHolder + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", paidTo='" + paidTo + '\'' +
                ", location='" + location + '\'' +
                ", time=" + time +
                ", amount=" + amount +
                '}';
    }
}
