package com.example.wedoogift.model;

import java.time.LocalDate;
import java.time.YearMonth;

public class MealDistribution extends Distribution {

    public MealDistribution(double amount, LocalDate distributionDate) {
        super(amount, distributionDate);
    }

    @Override
    public LocalDate getExpirationDate() {
    	int year;
        if (this.getDistributionDate().getMonthValue() > 2) {
        	year = this.getDistributionDate().getYear() + 1;
        } else {
        	year = this.getDistributionDate().getYear();
        }
        YearMonth yearMonth = YearMonth.of(year, 2);
        int dernierJour = yearMonth.lengthOfMonth();
        return LocalDate.of(year, 2, dernierJour);
    }
}