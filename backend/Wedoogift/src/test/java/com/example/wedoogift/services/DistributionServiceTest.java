package com.example.wedoogift.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.example.wedoogift.exceptions.DistributionException;
import com.example.wedoogift.interfaces.Distribution;
import com.example.wedoogift.model.Company;
import com.example.wedoogift.model.GiftDistribution;
import com.example.wedoogift.model.MealDistribution;
import com.example.wedoogift.model.User;

public class DistributionServiceTest {

	@Test
    void testDistributeGift() {
        DistributionService distributionService = new DistributionService();
        Company company = new Company(100, 50);
        User user = new User();
        GiftDistribution giftDistribution = new GiftDistribution(30, LocalDate.now());
        distributionService.distribute(company, user, 30, LocalDate.now(), giftDistribution);
        assertEquals(70, company.getGiftBalance());
        assertEquals(30, user.getGiftBalances().get(0).getAmount());
    }

    @Test
    void testDistributeMeal() {
        DistributionService distributionService = new DistributionService();
        Company company = new Company(100, 50);
        User user = new User();
        MealDistribution mealDistribution = new MealDistribution(20, LocalDate.now());
        distributionService.distribute(company, user, 20, LocalDate.now(), mealDistribution);
        assertEquals(30, company.getMealBalance());
        assertEquals(20, user.getMealBalances().get(0).getAmount());
    }
    
    @Test
    void testUnsupportedDistributionType() {
        Distribution unsupportedDistribution = mock(Distribution.class);
        DistributionService distributionService = new DistributionService();
        assertThrows(DistributionException.class, () -> {
            distributionService.distribute(new Company(100, 50), new User(), 50, LocalDate.now(), unsupportedDistribution);
        });
    }
    
}
