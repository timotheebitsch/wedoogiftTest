package com.example.wedoogift.model;

import java.time.LocalDate;

public abstract class Distribution {
    private double amount;
    private LocalDate distributionDate;
	
    public Distribution(double amount, LocalDate distributionDate) {
    	this.amount = amount;
    	this.distributionDate = distributionDate;
    }
    
	public double getAmount() {
		return amount;
	}
	
    public LocalDate getDistributionDate() {
		return distributionDate;
	}
    
    public abstract LocalDate getExpirationDate();
   
}
