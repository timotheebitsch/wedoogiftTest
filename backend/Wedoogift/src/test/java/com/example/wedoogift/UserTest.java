package com.example.wedoogift;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.wedoogift.objects.GiftDistribution;
import com.example.wedoogift.objects.MealDistribution;
import com.example.wedoogift.objects.User;

public class UserTest {
	
	@Test
    void testAddGiftBalance() {
        User user = new User();
        user.addGiftBalance(50, LocalDate.now());
        List<GiftDistribution> giftBalances = user.getGiftBalances();
        assertEquals(1, giftBalances.size());
        assertEquals(50, giftBalances.get(0).getAmount());
    }

    @Test
    void testAddMealBalance() {
        User user = new User();
        user.addMealBalance(30, LocalDate.now());
        List<MealDistribution> mealBalances = user.getMealBalances();
        assertEquals(1, mealBalances.size());
        assertEquals(30, mealBalances.get(0).getAmount());
    }

}