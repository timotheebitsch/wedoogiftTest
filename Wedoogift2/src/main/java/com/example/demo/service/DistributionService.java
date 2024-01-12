package com.example.demo.service;

import com.example.demo.controller.dto.CompanyDistribution;
import com.example.demo.controller.dto.ItemType;
import com.example.demo.entity.CompanyEntity;
import com.example.demo.entity.GiftDistributionEntity;
import com.example.demo.entity.MealDistributionEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.DistributionRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Service
public class DistributionService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private DistributionRepository distributionRepository;

    @Transactional
    public boolean distribute(CompanyDistribution companyDistribution)
    {
        boolean result = false;

        Optional<CompanyEntity> companyEntity = companyRepository.findById(companyDistribution.companyId());
        Optional<UserEntity> user = userRepository.findById(companyDistribution.userId());

        if (companyEntity.isPresent() && user.isPresent())
        {
            CompanyEntity company = companyEntity.get();

            if (ItemType.GIFT == companyDistribution.itemType())
            {
                if (company.getAmountGift() >= companyDistribution.amount())
                {
                    distributeGift(companyDistribution.amount(), user.get(), companyDistribution.distributionDate());
                    company.setAmountGift(company.getAmountGift() - companyDistribution.amount());
                    companyRepository.save(company);

                    result = true;
                }
            }
            else if (ItemType.MEAL == companyDistribution.itemType())
            {
                distributeMeal(companyDistribution.amount(), user.get(), companyDistribution.distributionDate());
                company.setAmountMeal(company.getAmountMeal() - companyDistribution.amount());
                companyRepository.save(company);

                result = true;
            }

            log.info("### Distribution ###");
            log.info(
                "Distributed: {} of {} from company {} to user {}",
                companyDistribution.amount(),
                companyDistribution.itemType(),
                company.getCompanyName(),
                user.get().getUserName()
            );

        }

        return result;
    }

    private void distributeGift(double amount, UserEntity user, LocalDate localDate)
    {
        GiftDistributionEntity giftDistributionEntity = new GiftDistributionEntity(amount, localDate);
        giftDistributionEntity.setUser(user);

        distributionRepository.save(giftDistributionEntity);
    }

    private void distributeMeal(double amount, UserEntity user, LocalDate localDate)
    {
        MealDistributionEntity mealDistributionEntity = new MealDistributionEntity(amount, localDate);
        mealDistributionEntity.setUser(user);

        distributionRepository.save(mealDistributionEntity);
    }
}

