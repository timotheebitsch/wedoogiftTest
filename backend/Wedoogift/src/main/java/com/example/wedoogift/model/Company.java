package com.example.wedoogift.model;


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
    
    public void setGiftBalance(double giftBalance) {
        this.giftBalance = giftBalance;
    }

    public void setMealBalance(double mealBalance) {
        this.mealBalance = mealBalance;
    }
}