package com.example.wedoogift.services;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.wedoogift.exceptions.DistributionException;
import com.example.wedoogift.model.Company;
import com.example.wedoogift.model.Distribution;
import com.example.wedoogift.model.GiftDistribution;
import com.example.wedoogift.model.MealDistribution;
import com.example.wedoogift.model.User;

public class DistributionService {
	
    private static final Logger logger = LoggerFactory.getLogger(DistributionService.class);
    
    public void distributeGift(Company company, User user, double amount, LocalDate distributionDate) {
        if (company.getGiftBalance() >= amount) {
            addGiftBalance(user, amount, distributionDate);
            deductGiftBalance(company, amount);
        } else {
            logger.warn("Insufficient gift balance in the company for distribution.");
        }
    }

    public void distributeMeal(Company company, User user, double amount, LocalDate distributionDate) {
        if (company.getMealBalance() >= amount) {
            addMealBalance(user, amount, distributionDate);
            deductMealBalance(company, amount);
        } else {
        	logger.warn("Insufficient meal balance in the company for distribution.");
        }
    }
    
    private void deductGiftBalance(Company company, double amount) {
    	company.setGiftBalance(company.getGiftBalance() - amount) ;
    }

    private void deductMealBalance(Company company, double amount) {
    	company.setMealBalance(company.getMealBalance() - amount) ;
    }
    
    public void addGiftBalance(User user, double amount, LocalDate distributionDate) {
    	user.getGiftBalances().add(new GiftDistribution(amount, distributionDate));
    }

    public void addMealBalance(User user, double amount, LocalDate distributionDate) {
    	user.getMealBalances().add(new MealDistribution(amount, distributionDate));
    }
}

