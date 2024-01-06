package com.example.wedoogift.objects;

import java.time.LocalDate;

public class Company {
	
    private double giftBalance;
    private double mealBalance;

    public Company(double giftBalance, double mealBalance) {
        this.giftBalance = giftBalance;
        this.mealBalance = mealBalance;
    }

    public double getGiftBalance() {
        return giftBalance;
    }

    public double getMealBalance() {
        return mealBalance;
    }

    public void distributeGift(User user, double amount, LocalDate distributionDate) {
        if (giftBalance >= amount) {
            user.addGiftBalance(amount, distributionDate);
            deductGiftBalance(amount);
        } else {
            System.out.println("Insufficient gift balance in the company for distribution.");
        }
    }

    public void distributeMeal(User user, double amount, LocalDate distributionDate) {
        if (mealBalance >= amount) {
            user.addMealBalance(amount, distributionDate);
            deductMealBalance(amount);
        } else {
            System.out.println("Insufficient meal balance in the company for distribution.");
        }
    }

    private void deductGiftBalance(double amount) {
        giftBalance -= amount;
    }

    private void deductMealBalance(double amount) {
        mealBalance -= amount;
    }
}