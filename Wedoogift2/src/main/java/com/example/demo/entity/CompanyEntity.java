package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "COMPANY")
public class CompanyEntity
{
    @Id
    private long id;

    @Column(name = "name")
    private String companyName;

    @Column(name = "amount_gift")
    private double amountGift;

    @Column(name = "amount_meal")
    private double amountMeal;
}
