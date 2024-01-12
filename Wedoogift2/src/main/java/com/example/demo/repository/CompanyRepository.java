package com.example.demo.repository;

import com.example.demo.entity.CompanyEntity;
import com.example.demo.entity.DistributionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository
    extends JpaRepository<CompanyEntity, Long>
{
}
