package com.example.wedoogift;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.example.wedoogift.objects.GiftDistribution;

public class GiftDistributionTest {
	
	@Test
    void testExpirationDate() {
        GiftDistribution giftDistribution = new GiftDistribution(100, LocalDate.of(2021, 6, 15));
        LocalDate expectedExpirationDate = LocalDate.of(2022, 6, 15);
        assertEquals(expectedExpirationDate, giftDistribution.getExpirationDate());
    }

}
