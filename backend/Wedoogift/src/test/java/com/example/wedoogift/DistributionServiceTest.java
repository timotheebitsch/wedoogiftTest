package com.example.wedoogift;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.example.wedoogift.objects.Company;
import com.example.wedoogift.objects.GiftDistribution;
import com.example.wedoogift.objects.MealDistribution;
import com.example.wedoogift.objects.User;
import com.example.wedoogift.services.DistributionService;

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
}
