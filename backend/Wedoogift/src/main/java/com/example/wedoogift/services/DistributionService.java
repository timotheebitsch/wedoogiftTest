package com.example.wedoogift.services;

import java.time.LocalDate;

import com.example.wedoogift.interfaces.Distribution;
import com.example.wedoogift.objects.Company;
import com.example.wedoogift.objects.GiftDistribution;
import com.example.wedoogift.objects.MealDistribution;
import com.example.wedoogift.objects.User;

public class DistributionService {
    public void distribute(Company company, User user, double amount, LocalDate distributionDate, Distribution distribution) {
        if (distribution instanceof GiftDistribution) {
            if (company.getGiftBalance() >= amount) {
                company.distributeGift(user, amount, distributionDate);
            } else {
                System.out.println("Insufficient gift balance in the company for distribution.");
            }
        } else if (distribution instanceof MealDistribution) {
            if (company.getMealBalance() >= amount) {
                company.distributeMeal(user, amount, distributionDate);
            } else {
                System.out.println("Insufficient meal balance in the company for distribution.");
            }
        } else {
            System.out.println("Unsupported distribution type.");
        }
    }
}

