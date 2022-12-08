package com.redegal.priceApplicator.PriceApplicatorIntegrationTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redegal.priceApplicator.application.dtos.PriceSeletedResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceRestAdapterIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenOkStatusWhenFoundPrice() throws Exception
    {
        String criteria = "?applicationDate=2020-06-14T16:00:00"
                +"&productId=35455&brandId=1";
        MvcResult resultPrice =
                mockMvc.perform(get("/api/v1/pricetoapply" + criteria)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = resultPrice
                .getResponse()
                .getContentAsString();

        PriceSeletedResponseDto priceFound = objectMapper.readValue(
                contentAsString,
                new TypeReference<>() {
                }
        );
        assertThat(priceFound.getPrice()).isEqualTo(BigDecimal.valueOf(25.45));
    }
    @Test

    void given404BadRequestStatusWhenNotFoundPrice() throws Exception
    {
        String criteria = "?applicationDate=2022-06-14T16:00:00"
                +"&productId=35455&brandId=1";
        MvcResult resultPrice =
                mockMvc.perform(get("/api/v1/pricetoapply" + criteria)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
                        .andReturn();
        String contentAsString = resultPrice
                .getResponse()
                .getContentAsString();
    }
}
