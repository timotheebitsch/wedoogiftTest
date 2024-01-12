package com.example.demo.service;

import com.example.demo.entity.CompanyEntity;
import com.example.demo.entity.DistributionEntity;
import com.example.demo.entity.GiftDistributionEntity;
import com.example.demo.entity.MealDistributionEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Service
public class BalanceCalculatorService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public void printCompanyBalance(Long companyId)
    {
        Optional<CompanyEntity> company = companyRepository.findById(companyId);

        company.ifPresent(this::printCompany);
    }

    private void printCompany(CompanyEntity company)
    {
        log.info(
            "Company {} has {} meal(s) and {} gift(s)",
            company.getCompanyName(),
            company.getAmountMeal(),
            company.getAmountGift()
        );
    }

    public void printUserBalance(Long userId)
    {
        Optional<UserEntity> user = userRepository.findById(userId);

        user.ifPresent(this::printUser);
    }

    private void printUser(UserEntity user)
    {
        log.info(
            "User {} has {} meal(s) and {} gift(s)",
            user.getUserName(),
            calculateMealBalance(user),
            calculateGiftBalance(user)
            );
    }

    public void printAllBalance()
    {
        log.info("### Balance ###");
        companyRepository.findAll().forEach(this::printCompany);
        userRepository.findAll().forEach(this::printUser);
    }

    public double calculateGiftBalance(UserEntity user)
    {
        return user.getDistributions().stream()
            .filter(distributionEntity -> distributionEntity instanceof GiftDistributionEntity)
            .filter(distribution -> !distribution.getExpirationDate().isBefore(LocalDate.now()))
            .mapToDouble(DistributionEntity::getAmount)
            .sum();
    }

    public double calculateMealBalance(UserEntity user)
    {
        return user.getDistributions().stream()
            .filter(distributionEntity -> distributionEntity instanceof MealDistributionEntity)
            .filter(distribution -> !distribution.getExpirationDate().isBefore(LocalDate.now()))
            .mapToDouble(DistributionEntity::getAmount)
            .sum();
    }
}