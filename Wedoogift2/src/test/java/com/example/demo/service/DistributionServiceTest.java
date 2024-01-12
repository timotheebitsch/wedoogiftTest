package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.controller.dto.CompanyDistribution;
import com.example.demo.controller.dto.ItemType;
import com.example.demo.entity.CompanyEntity;
import com.example.demo.entity.GiftDistributionEntity;
import com.example.demo.entity.MealDistributionEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.DistributionRepository;
import com.example.demo.repository.UserRepository;

public class DistributionServiceTest {
	
	@Mock
    private CompanyRepository companyRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private DistributionRepository distributionRepository;

    @InjectMocks
    private DistributionService distributionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDistributeGift() {
        long userId = 2L;
        double amount = 10.0;
        LocalDate distributionDate = LocalDate.now();

        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setAmountGift(20);
        Optional<CompanyEntity> companyEntityOptional = Optional.of(companyEntity);


        when(companyRepository.findById(anyLong())).thenReturn(companyEntityOptional);

        UserEntity userEntity = new UserEntity();
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        CompanyDistribution companyDistribution = new CompanyDistribution(userId, userId, ItemType.GIFT, distributionDate, amount/* Set other properties */);

        boolean result = distributionService.distribute(companyDistribution);
        assert(result);
        verify(distributionRepository, times(1)).save(any(GiftDistributionEntity.class));
        verify(distributionRepository, times(0)).save(any(MealDistributionEntity.class));
        verify(companyRepository, times(1)).save(any(CompanyEntity.class));
    }

    @Test
    void testDistributeMeal() {
        long userId = 2L;
        double amount = 10.0;
        LocalDate distributionDate = LocalDate.now();

        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setAmountMeal(20);
        Optional<CompanyEntity> companyEntityOptional = Optional.of(companyEntity);


        when(companyRepository.findById(anyLong())).thenReturn(companyEntityOptional);

        UserEntity userEntity = new UserEntity();
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        CompanyDistribution companyDistribution = new CompanyDistribution(userId, userId, ItemType.MEAL, distributionDate, amount/* Set other properties */);

        boolean result = distributionService.distribute(companyDistribution);
        assert(result);
        verify(distributionRepository, times(1)).save(any(MealDistributionEntity.class));
        verify(distributionRepository, times(0)).save(any(GiftDistributionEntity.class));
        verify(companyRepository, times(1)).save(any(CompanyEntity.class));
    }
}
	

