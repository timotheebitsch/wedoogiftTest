package com.example.wedoogift.model;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private List<GiftDistribution> giftBalances = new ArrayList<>();
    private List<MealDistribution> mealBalances = new ArrayList<>();

    public List<GiftDistribution> getGiftBalances() {
        return giftBalances;
    }

    public List<MealDistribution> getMealBalances() {
        return mealBalances;
    }


}