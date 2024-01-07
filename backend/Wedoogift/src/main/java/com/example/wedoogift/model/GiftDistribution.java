package com.example.wedoogift.model;

import java.time.LocalDate;

public class GiftDistribution extends Distribution {

    public GiftDistribution(double amount, LocalDate distributionDate) {
        super(amount, distributionDate);
    }

    @Override
    public LocalDate getExpirationDate() {
        return this.getDistributionDate().plusDays(365);
    }
}