package com.example.wedoogift.services;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.wedoogift.exceptions.DistributionException;
import com.example.wedoogift.interfaces.Distribution;
import com.example.wedoogift.model.Company;
import com.example.wedoogift.model.GiftDistribution;
import com.example.wedoogift.model.MealDistribution;
import com.example.wedoogift.model.User;

public class DistributionService {
	
    private static final Logger logger = LoggerFactory.getLogger(DistributionService.class);

    public void distribute(Company company, User user, double amount, LocalDate distributionDate, Distribution distribution) {
        if (distribution instanceof GiftDistribution) {
            if (company.getGiftBalance() >= amount) {
                company.distributeGift(user, amount, distributionDate);           
            }
        } else if (distribution instanceof MealDistribution) {
            if (company.getMealBalance() >= amount) {
                company.distributeMeal(user, amount, distributionDate);
            }
        } else {
        	logger.error("Unsupported distribution type.");
            throw new DistributionException("Unsupported distribution type.");
        }
    }
}

