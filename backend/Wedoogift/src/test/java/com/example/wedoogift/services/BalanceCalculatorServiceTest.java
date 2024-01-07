package com.example.wedoogift.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.example.wedoogift.model.Company;
import com.example.wedoogift.model.User;

public class BalanceCalculatorServiceTest {
	
	DistributionService distributionService = new DistributionService();

	@Test
    void testCalculateGiftBalance() {
        BalanceCalculatorService balanceCalculatorService = new BalanceCalculatorService();
        User user = new User();
        distributionService.addGiftBalance(user, 50, LocalDate.now());
        distributionService.addGiftBalance(user, 30, LocalDate.now().minusDays(100));
        double calculatedBalance = balanceCalculatorService.calculateGiftBalance(user);
        assertEquals(80, calculatedBalance);
    }
	
	@Test
    void testCalculateGiftBalanceWithExpired() {
        BalanceCalculatorService balanceCalculatorService = new BalanceCalculatorService();
        User user = new User();
        distributionService.addGiftBalance(user, 50, LocalDate.now());
        distributionService.addGiftBalance(user, 30, LocalDate.now().minusDays(100));
        distributionService.addGiftBalance(user, 30, LocalDate.now().minusDays(366));
        double calculatedBalance = balanceCalculatorService.calculateGiftBalance(user);
        assertEquals(80, calculatedBalance);
    }

    @Test
    void testCalculateMealBalance() {
        BalanceCalculatorService balanceCalculatorService = new BalanceCalculatorService();
        User user = new User();
        distributionService.addMealBalance(user, 20, LocalDate.now());
        distributionService.addMealBalance(user, 40, LocalDate.now().minusMonths(6));
        double calculatedBalance = balanceCalculatorService.calculateMealBalance(user);
        assertEquals(60, calculatedBalance);
    }
    
    @Test
    void testCalculateMealBalanceWithExpired() {
        BalanceCalculatorService balanceCalculatorService = new BalanceCalculatorService();
        User user = new User();
        distributionService.addMealBalance(user, 20, LocalDate.now());
        distributionService.addMealBalance(user, 40, LocalDate.now().minusMonths(6));
        distributionService.addMealBalance(user, 40, LocalDate.now().minusMonths(365));
        double calculatedBalance = balanceCalculatorService.calculateMealBalance(user);
        assertEquals(60, calculatedBalance);
    }
	
}
