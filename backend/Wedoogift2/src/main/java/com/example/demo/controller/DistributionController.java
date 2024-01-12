package com.example.demo.controller;

import com.example.demo.controller.dto.CompanyDistribution;
import com.example.demo.service.BalanceCalculatorService;
import com.example.demo.service.DistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DistributionController
{
    @Autowired
    private DistributionService distributionService;

    @Autowired
    private BalanceCalculatorService balanceCalculatorService;

    @PostMapping("/deposit")
    private ResponseEntity<String> depositItem(@RequestBody CompanyDistribution companyDistribution)
    {
        boolean distributionSucceed = distributionService.distribute(companyDistribution);

        if (distributionSucceed)
        {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/print-company-balance/{id}")
    private void printCompanyBalance(@PathVariable Long id)
    {
        balanceCalculatorService.printCompanyBalance(id);
    }

    @GetMapping("/print-user-balance/{id}")
    private void printUserBalance(@PathVariable Long id)
    {
        balanceCalculatorService.printUserBalance(id);
    }

    @GetMapping("/print-all-balance")
    private void printAllBalance()
    {
        balanceCalculatorService.printAllBalance();
    }
}
