package com.example.wedoogift.services;

import java.time.LocalDate;

import com.example.wedoogift.objects.User;

public class BalanceCalculatorService {
    public double calculateGiftBalance(User user, LocalDate currentDate) {
        return user.calculateGiftBalance(currentDate);
    }

    public double calculateMealBalance(User user, LocalDate currentDate) {
        return user.calculateMealBalance(currentDate);
    }
}