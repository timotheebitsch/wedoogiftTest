package com.example.wedoogift.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.wedoogift.exceptions.DistributionException;
import com.example.wedoogift.model.Company;
import com.example.wedoogift.model.Distribution;
import com.example.wedoogift.model.GiftDistribution;
import com.example.wedoogift.model.MealDistribution;
import com.example.wedoogift.model.User;

public class DistributionServiceTest {

    DistributionService distributionService = new DistributionService();

	
	@Test
    void testDistributeGift() {
        Company company = new Company(100, 50);
        User user = new User();
        distributionService.distributeGift(company, user, 30, LocalDate.now());
        assertEquals(70, company.getGiftBalance());
        assertEquals(30, user.getGiftBalances().get(0).getAmount());
    }

    @Test
    void testDistributeMeal() {
        Company company = new Company(100, 50);
        User user = new User();
        distributionService.distributeMeal(company, user, 20, LocalDate.now());
        assertEquals(30, company.getMealBalance());
        assertEquals(20, user.getMealBalances().get(0).getAmount());
    }
    
    @Test
    void testAddGiftBalance() {
        User user = new User();
        distributionService.addGiftBalance(user, 50, LocalDate.now());
        List<GiftDistribution> giftBalances = user.getGiftBalances();
        assertEquals(1, giftBalances.size());
        assertEquals(50, giftBalances.get(0).getAmount());
    }

    @Test
    void testAddMealBalance() {
        User user = new User();
        distributionService.addMealBalance(user, 30, LocalDate.now());
        List<MealDistribution> mealBalances = user.getMealBalances();
        assertEquals(1, mealBalances.size());
        assertEquals(30, mealBalances.get(0).getAmount());
    }
    
    
    @Test
    void testDistributeMealWithInsufficientBalance() {
        Company company = new Company(100, 50);
        User user = new User();
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        distributionService.distributeMeal(company, user, 60, LocalDate.now());
        System.setOut(System.out);
        assertTrue(outputStreamCaptor.toString().trim().contains("Insufficient meal balance in the company for distribution."));
        assertEquals(50, company.getMealBalance());
    }
    
    @Test
    void testDistributeGiftWithInsufficientBalance() {
        Company company = new Company(100, 50);
        User user = new User();
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        distributionService.distributeGift(company, user, 150, LocalDate.now());
        System.setOut(System.out);
        assertTrue(outputStreamCaptor.toString().trim().contains("Insufficient gift balance in the company for distribution."));
        assertEquals(100, company.getGiftBalance());
    }
    
}
