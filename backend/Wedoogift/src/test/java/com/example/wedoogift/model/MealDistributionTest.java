package com.example.wedoogift.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.example.wedoogift.model.MealDistribution;

public class MealDistributionTest {
	
	 @Test
	    void testExpirationDate() {
	        MealDistribution mealDistribution = new MealDistribution(50, LocalDate.of(2020, 1, 1));
	        LocalDate expectedExpirationDate = LocalDate.of(2021, 2, 28);
	        assertEquals(expectedExpirationDate, mealDistribution.getExpirationDate());
	    }
	 
	 @Test
	    void testGetDistributionDate() {
	        LocalDate expectedDate = LocalDate.of(2022, 1, 15);
	        MealDistribution mealDistribution = new MealDistribution(50, expectedDate);
	        LocalDate actualDate = mealDistribution.getDistributionDate();
	        assertEquals(expectedDate, actualDate);
	    }

}
