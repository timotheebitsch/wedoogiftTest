package com.example.demo.controller.dto;

import java.time.LocalDate;

public record CompanyDistribution(Long companyId, Long userId, ItemType itemType, LocalDate distributionDate, double amount)
{
}
