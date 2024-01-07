package com.example.wedoogift.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class CompanyTest {
	
	@Test
    void testDistributeGift() {
        Company company = new Company(100, 50);
        User user = new User();
        company.distributeGift(user, 30, LocalDate.now());
        assertEquals(70, company.getGiftBalance());
        assertEquals(30, user.getGiftBalances().get(0).getAmount());
    }

    @Test
    void testDistributeMeal() {
        Company company = new Company(100, 50);
        User user = new User();
        company.distributeMeal(user, 20, LocalDate.now());
        assertEquals(30, company.getMealBalance());
        assertEquals(20, user.getMealBalances().get(0).getAmount());
    }
    
    @Test
    void testDistributeMealWithInsufficientBalance() {
        Company company = new Company(100, 50);
        User user = new User();
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        company.distributeMeal(user, 60, LocalDate.now());
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
        company.distributeGift(user, 150, LocalDate.now());
        System.setOut(System.out);
        assertTrue(outputStreamCaptor.toString().trim().contains("Insufficient gift balance in the company for distribution."));
        assertEquals(100, company.getGiftBalance());
    }
     

}
