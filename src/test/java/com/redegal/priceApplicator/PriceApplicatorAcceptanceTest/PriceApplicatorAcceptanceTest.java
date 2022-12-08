package com.redegal.priceApplicator.PriceApplicatorAcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redegal.priceApplicator.application.dtos.CriteriaRequestDto;
import com.redegal.priceApplicator.application.dtos.PriceSeletedResponseDto;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.web.client.RestTemplate;
import static org.springframework.http.HttpStatus.OK;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceApplicatorAcceptanceTest {

    @LocalServerPort
    int serverPort;

    @Autowired
    private ObjectMapper objectMapper;

    private RestTemplate restTemplate;

    private static String url;
    List<String> criteria;

    // - Test 1: petición a las 10:00 del día 14 del producto 35455 brand 1 (ZARA)
    // - Test 2: petición a las 16:00 del día 14 del producto 35455 brand 1 (ZARA)
    // - Test 3: petición a las 21:00 del día 14 del producto 35455 brand 1 (ZARA)
    // - Test 4: petición a las 10:00 del día 15 del producto 35455 brand 1 (ZARA)
    // - Test 5: petición a las 21:00 del día 16 del producto 35455 brand 1 (ZARA)

    @BeforeEach
    void setUp()
    {
        restTemplate = new RestTemplate();
        url = "http://localhost:" + serverPort + "/api/v1/pricetoapply";
    }

    @ParameterizedTest
    @CsvSource({
    "?applicationDate=2020-06-14T10:00:00&productId=35455&brandId=1,    35.50",
    "?applicationDate=2020-06-14T16:00:00&productId=35455&brandId=1,   25.45",
    "?applicationDate=2020-06-14T21:00:00&productId=35455&brandId=1,   35.50",
    "?applicationDate=2020-06-15T10:00:00&productId=35455&brandId=1,   30.50",
    "?applicationDate=2020-06-16T21:00:00&productId=35455&brandId=1,   38.95",
    })
    void testCsv(String filter, BigDecimal price) throws JsonProcessingException
    {
        ResponseEntity responseEntity = restTemplate
                .getForEntity(url+ filter, String.class);
        PriceSeletedResponseDto priceFound =
                objectMapper.readValue(responseEntity.getBody().toString(),
                        PriceSeletedResponseDto.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
        assertThat(priceFound.getPrice()).isEqualTo(price);
    }
}
