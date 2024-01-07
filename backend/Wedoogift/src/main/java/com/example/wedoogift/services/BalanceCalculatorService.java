package com.example.wedoogift.services;

import java.time.LocalDate;

import com.example.wedoogift.interfaces.Distribution;
import com.example.wedoogift.model.User;

public class BalanceCalculatorService {
    
    public double calculateGiftBalance(User user) {
    	 return user.getGiftBalances().stream()
                .filter(distribution -> !distribution.getExpirationDate().isBefore(LocalDate.now()))
                .mapToDouble(Distribution::getAmount)
                .sum();
    }
    
    public double calculateMealBalance(User user) {
        return user.getMealBalances().stream()
                .filter(distribution -> !distribution.getExpirationDate().isBefore(LocalDate.now()))
                .mapToDouble(Distribution::getAmount)
                .sum();
    }
}