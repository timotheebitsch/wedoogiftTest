package com.example.wedoogift.model;

import java.time.LocalDate;

import com.example.wedoogift.interfaces.Distribution;

public class GiftDistribution implements Distribution {
    private double amount;
    private LocalDate distributionDate;

    public GiftDistribution(double amount, LocalDate distributionDate) {
        this.amount = amount;
        this.distributionDate = distributionDate;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public LocalDate getDistributionDate() {
        return distributionDate;
    }

    @Override
    public LocalDate getExpirationDate() {
        return distributionDate.plusDays(365);
    }
}