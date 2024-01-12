package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.service.BalanceCalculatorService;
import com.example.demo.service.DistributionService;



@RunWith(SpringRunner.class)
@WebMvcTest(DistributionController.class)
public class DistributionControllerTest {

	@Autowired
    private MockMvc mvc;

    @MockBean
    private DistributionService distributionService;

    @MockBean
    private BalanceCalculatorService balanceCalculatorService;
    @Captor
    private ArgumentCaptor<Long> idCaptor;
    

    @Test
    public void printCompanyBalanceTest() throws Exception {
        this.mvc.perform(get("http://localhost:8088/print-company-balance/1"))
                .andExpect(status().isOk());
        
        verify(balanceCalculatorService).printCompanyBalance(idCaptor.capture());

        Long capturedId = idCaptor.getValue();
        assertTrue(capturedId.equals(1L));

    }
    
    @Test
    public void printUserBalanceTest() throws Exception {
        this.mvc.perform(get("http://localhost:8088/print-user-balance/1"))
                .andExpect(status().isOk());
        
        verify(balanceCalculatorService).printUserBalance(idCaptor.capture());

        
        Long capturedId = idCaptor.getValue();
        assertTrue(capturedId.equals(1L));
    }
    
    @Test
    public void printAllBalanceTest() throws Exception {
        this.mvc.perform(get("http://localhost:8088/print-all-balance"))
                .andExpect(status().isOk());
    }
}

