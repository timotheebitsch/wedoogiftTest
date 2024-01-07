package com.example.wedoogift.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.example.wedoogift.model.GiftDistribution;

public class GiftDistributionTest {
	
	@Test
    void testExpirationDate() {
        GiftDistribution giftDistribution = new GiftDistribution(100, LocalDate.of(2021, 6, 15));
        LocalDate expectedExpirationDate = LocalDate.of(2022, 6, 15);
        assertEquals(expectedExpirationDate, giftDistribution.getExpirationDate());
    }
	
	@Test
    void testGetDistributionDate() {
        LocalDate expectedDate = LocalDate.of(2022, 1, 15);
        GiftDistribution giftDistribution = new GiftDistribution(50, expectedDate);
        LocalDate actualDate = giftDistribution.getDistributionDate();
        assertEquals(expectedDate, actualDate);
    }

}
