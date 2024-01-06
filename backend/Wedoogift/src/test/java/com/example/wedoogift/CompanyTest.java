package com.example.wedoogift;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.example.wedoogift.objects.Company;
import com.example.wedoogift.objects.User;

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

}
