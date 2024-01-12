package com.example.demo.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("G")
public class GiftDistributionEntity
    extends DistributionEntity
{
    public GiftDistributionEntity()
    {
    }

    public GiftDistributionEntity(double amount, LocalDate distributionDate)
    {
        super(amount, distributionDate);
    }

    @Override
    public LocalDate getExpirationDate()
    {
        return this.getDistributionDate().plusDays(365);
    }
}