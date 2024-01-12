package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "DISTRIBUTION")
@NoArgsConstructor
public abstract class DistributionEntity
{
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "amount")
    private double amount;

    @Column(name = "distribution_date")
    private LocalDate distributionDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public DistributionEntity(double amount, LocalDate distributionDate)
    {
        this.amount = amount;
        this.distributionDate = distributionDate;
    }

    public abstract LocalDate getExpirationDate();

    @Override
    public String toString()
    {
        return "DistributionEntity{" +
            "id=" + id +
            ", amount=" + amount +
            ", distributionDate=" + distributionDate +
            ", user=" + user +
            '}';
    }
}
