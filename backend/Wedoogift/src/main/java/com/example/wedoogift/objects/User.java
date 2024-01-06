package com.example.wedoogift.objects;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.wedoogift.interfaces.Distribution;

public class User {
	
    private List<GiftDistribution> giftBalances;
    private List<MealDistribution> mealBalances;

    public User() {
        this.giftBalances = new ArrayList<>();
        this.mealBalances = new ArrayList<>();
    }

    public List<GiftDistribution> getGiftBalances() {
        return giftBalances;
    }

    public List<MealDistribution> getMealBalances() {
        return mealBalances;
    }

    public void addGiftBalance(double amount, LocalDate distributionDate) {
        giftBalances.add(new GiftDistribution(amount, distributionDate));
    }

    public void addMealBalance(double amount, LocalDate distributionDate) {
        mealBalances.add(new MealDistribution(amount, distributionDate));
    }

    public double calculateGiftBalance(LocalDate currentDate) {
        return giftBalances.stream()
                .filter(distribution -> !distribution.getExpirationDate().isBefore(currentDate))
                .mapToDouble(Distribution::getAmount)
                .sum();
    }

    public double calculateMealBalance(LocalDate currentDate) {
        return mealBalances.stream()
                .filter(distribution -> !distribution.getExpirationDate().isBefore(currentDate))
                .mapToDouble(Distribution::getAmount)
                .sum();
    }
}