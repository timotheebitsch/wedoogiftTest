package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.controller.dto.CompanyDistribution;
import com.example.demo.controller.dto.ItemType;
import com.example.demo.service.BalanceCalculatorService;
import com.example.demo.service.DistributionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

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
    public void printCompanyBalanceTest_200() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/print-company-balance/1"))
                .andExpect(status().isOk());
        
        verify(balanceCalculatorService).printCompanyBalance(idCaptor.capture());

        Long capturedId = idCaptor.getValue();
        assertTrue(capturedId.equals(1L));

    }
    
    @Test
    public void printUserBalanceTest_200() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/print-user-balance/1"))
                .andExpect(status().isOk());
        
        verify(balanceCalculatorService).printUserBalance(idCaptor.capture());

        
        Long capturedId = idCaptor.getValue();
        assertTrue(capturedId.equals(1L));
    }
      
    @Test
    public void printAllBalanceTest_200() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/print-all-balance"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void depositItemTest_200() throws Exception {
        CompanyDistribution companyDistribution = new CompanyDistribution(1L, 1L, ItemType.GIFT, LocalDate.of(2024, 05, 01) , 60);
        when(distributionService.distribute(companyDistribution)).thenReturn(true);

        this.mvc.perform(MockMvcRequestBuilders.post("/deposit")
        .content(asJsonString(companyDistribution))
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }
    
    @Test
    public void depositItemTest_400() throws Exception {
    	CompanyDistribution companyDistribution = new CompanyDistribution(1L, 1L, ItemType.GIFT, LocalDate.of(2024, 05, 01) , 60);
        when(distributionService.distribute(eq(companyDistribution))).thenReturn(true);

        //With mistake in distributionDate spelling
        this.mvc.perform(MockMvcRequestBuilders.post("/deposit")
        .content("{\r\n"
        		+ "    \"companyId\":1,\r\n"
        		+ "    \"userId\":1,\r\n"
        		+ "    \"itemType\":\"GIFT\",\r\n"
        		+ "    \"distribuionDate\":\"2024-05-01\",\r\n"
        		+ "    \"amount\":60\r\n"
        		+ "}")
		.contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    }
    
    @Test
    public void depositItemTest_404() throws Exception {
    	CompanyDistribution companyDistribution = new CompanyDistribution(1L, 1L, ItemType.GIFT, LocalDate.of(2024, 05, 01) , 60);
        when(distributionService.distribute(eq(companyDistribution))).thenReturn(true);

        //With mistake in url
        this.mvc.perform(MockMvcRequestBuilders.post("/deposi")
                .content(asJsonString(companyDistribution))
        		.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    
    public static String asJsonString(final Object obj) {
    	ObjectMapper objectMapper = new ObjectMapper();
    	objectMapper.registerModule(new JavaTimeModule());
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

