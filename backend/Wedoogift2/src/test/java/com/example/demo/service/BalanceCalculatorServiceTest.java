package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.entity.DistributionEntity;
import com.example.demo.entity.GiftDistributionEntity;
import com.example.demo.entity.MealDistributionEntity;
import com.example.demo.entity.UserEntity;

public class BalanceCalculatorServiceTest {

    private BalanceCalculatorService balanceCalculatorService;

    @BeforeEach
    public void setUp() {
        balanceCalculatorService = new BalanceCalculatorService();
    }

    @Test
    public void testCalculateGiftBalance() {
    UserEntity userEntity = new UserEntity();
    List<DistributionEntity> distributions = new ArrayList<DistributionEntity>();
    distributions.add(new GiftDistributionEntity(50, LocalDate.now()));
    distributions.add(new GiftDistributionEntity(100, LocalDate.now()));
    distributions.add(new GiftDistributionEntity(150, LocalDate.now()));
    userEntity.setDistributions(distributions);
    Double balanceTest =balanceCalculatorService.calculateGiftBalance(userEntity);
    assertEquals(balanceTest, 300);
    }
    
    @Test
    public void testCalculateGiftBalancewithExpired() {
    UserEntity userEntity = new UserEntity();
    List<DistributionEntity> distributions = new ArrayList<DistributionEntity>();
    distributions.add(new GiftDistributionEntity(50, LocalDate.now()));
    distributions.add(new GiftDistributionEntity(100, LocalDate.now()));
    distributions.add(new GiftDistributionEntity(150, LocalDate.of(2022, 01, 01)));
    userEntity.setDistributions(distributions);
    Double balanceTest =balanceCalculatorService.calculateGiftBalance(userEntity);
    assertEquals(balanceTest, 150);
    }
    
    @Test
    public void testCalculateMealBalance() {
    UserEntity userEntity = new UserEntity();
    List<DistributionEntity> distributions = new ArrayList<DistributionEntity>();
    distributions.add(new MealDistributionEntity(50, LocalDate.now()));
    distributions.add(new MealDistributionEntity(100, LocalDate.now()));
    distributions.add(new MealDistributionEntity(150, LocalDate.now()));
    userEntity.setDistributions(distributions);
    Double balanceTest =balanceCalculatorService.calculateMealBalance(userEntity);
    assertEquals(balanceTest, 300);
    }
    
    @Test
    public void testCalculateMealBalancewithExpired() {
    UserEntity userEntity = new UserEntity();
    List<DistributionEntity> distributions = new ArrayList<DistributionEntity>();
    distributions.add(new MealDistributionEntity(50, LocalDate.now()));
    distributions.add(new MealDistributionEntity(100, LocalDate.now()));
    distributions.add(new MealDistributionEntity(150, LocalDate.of(2023, 01, 01)));
    userEntity.setDistributions(distributions);
    Double balanceTest =balanceCalculatorService.calculateMealBalance(userEntity);
    assertEquals(balanceTest, 150);
    }
    
    @Test
    public void testCalculateMealBalancewithNoExpired() {
    UserEntity userEntity = new UserEntity();
    List<DistributionEntity> distributions = new ArrayList<DistributionEntity>();
    distributions.add(new MealDistributionEntity(50, LocalDate.now()));
    distributions.add(new MealDistributionEntity(100, LocalDate.now()));
    distributions.add(new MealDistributionEntity(150, LocalDate.of(2023, 03, 01)));
    userEntity.setDistributions(distributions);
    Double balanceTest =balanceCalculatorService.calculateMealBalance(userEntity);
    assertEquals(balanceTest, 300);
    }
    
}