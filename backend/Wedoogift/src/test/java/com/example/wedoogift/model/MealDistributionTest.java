package com.example.wedoogift.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.example.wedoogift.model.MealDistribution;

public class MealDistributionTest {
	
	 @Test
	    void testExpirationDateSameYear() {
	        MealDistribution mealDistribution = new MealDistribution(50, LocalDate.of(2023, 1, 1));
	        LocalDate expectedExpirationDate = LocalDate.of(2023, 2, 28);
	        assertEquals(expectedExpirationDate, mealDistribution.getExpirationDate());
	    }
	 
	 @Test
	    void testExpirationDateNextYear() {
	        MealDistribution mealDistribution = new MealDistribution(50, LocalDate.of(2023, 3, 1));
	        LocalDate expectedExpirationDate = LocalDate.of(2024, 2, 29);
	        assertEquals(expectedExpirationDate, mealDistribution.getExpirationDate());
	    }
	 
	 @Test
	    void testExpirationDateSameYearSameMonth() {
	        MealDistribution mealDistribution = new MealDistribution(50, LocalDate.of(2023, 2, 15));
	        LocalDate expectedExpirationDate = LocalDate.of(2023, 2, 28);
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
