package com.example.wedoogift;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.example.wedoogift.objects.User;
import com.example.wedoogift.services.BalanceCalculatorService;

public class BalanceCalculatorServiceTest {

	@Test
    void testCalculateGiftBalance() {
        BalanceCalculatorService balanceCalculatorService = new BalanceCalculatorService();
        User user = new User();
        user.addGiftBalance(50, LocalDate.now());
        user.addGiftBalance(30, LocalDate.now().minusDays(100));
        double calculatedBalance = balanceCalculatorService.calculateGiftBalance(user, LocalDate.now().plusDays(50));
        assertEquals(80, calculatedBalance);
    }
	
	@Test
    void testCalculateGiftBalanceWithExpired() {
        BalanceCalculatorService balanceCalculatorService = new BalanceCalculatorService();
        User user = new User();
        user.addGiftBalance(50, LocalDate.now());
        user.addGiftBalance(30, LocalDate.now().minusDays(100));
        user.addGiftBalance(30, LocalDate.now().minusDays(366));
        double calculatedBalance = balanceCalculatorService.calculateGiftBalance(user, LocalDate.now().plusDays(50));
        assertEquals(80, calculatedBalance);
    }

    @Test
    void testCalculateMealBalance() {
        BalanceCalculatorService balanceCalculatorService = new BalanceCalculatorService();
        User user = new User();
        user.addMealBalance(20, LocalDate.now());
        user.addMealBalance(40, LocalDate.now().minusMonths(6));
        double calculatedBalance = balanceCalculatorService.calculateMealBalance(user, LocalDate.now());
        assertEquals(60, calculatedBalance);
    }
    
    @Test
    void testCalculateMealBalanceWithExpired() {
        BalanceCalculatorService balanceCalculatorService = new BalanceCalculatorService();
        User user = new User();
        user.addMealBalance(20, LocalDate.now());
        user.addMealBalance(40, LocalDate.now().minusMonths(6));
        user.addMealBalance(40, LocalDate.now().minusMonths(365));
        double calculatedBalance = balanceCalculatorService.calculateMealBalance(user, LocalDate.now());
        assertEquals(60, calculatedBalance);
    }
	
}
