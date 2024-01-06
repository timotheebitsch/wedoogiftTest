package com.example.wedoogift;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.example.wedoogift.objects.MealDistribution;

public class MealDistributionTest {
	
	 @Test
	    void testExpirationDate() {
	        MealDistribution mealDistribution = new MealDistribution(50, LocalDate.of(2020, 1, 1));
	        LocalDate expectedExpirationDate = LocalDate.of(2021, 2, 28);
	        assertEquals(expectedExpirationDate, mealDistribution.getExpirationDate());
	    }

}
