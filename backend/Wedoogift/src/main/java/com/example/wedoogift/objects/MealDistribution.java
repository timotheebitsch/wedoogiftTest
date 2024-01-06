package com.example.wedoogift.objects;

import java.time.LocalDate;

import com.example.wedoogift.interfaces.Distribution;

public class MealDistribution implements Distribution {
    private double amount;
    private LocalDate distributionDate;

    public MealDistribution(double amount, LocalDate distributionDate) {
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
        return distributionDate.plusMonths(14).withDayOfMonth(1).minusDays(1);
    }
}