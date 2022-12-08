package com.redegal.priceApplicator.AdaptersUnitTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.redegal.priceApplicator.application.dtos.CriteriaRequestDto;
import com.redegal.priceApplicator.application.repositories.PriceRepository;
import com.redegal.priceApplicator.application.services.PriceApplicatorServiceImpl;
import com.redegal.priceApplicator.domain.exceptions.PriceNotFoundException;
import com.redegal.priceApplicator.domain.models.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PriceApplicatorUnitTest {

    Price price;
    CriteriaRequestDto filter;
    PriceRepository priceRepository = Mockito.mock(PriceRepository.class);
    @InjectMocks
    PriceApplicatorServiceImpl priceApplicatorService;

    @BeforeEach
    void setUp()
    {
        filter = CriteriaRequestDto.builder()
                .applicationDate(LocalDateTime.parse("2020-02-14T10:00:00"))
                .brandId(1)
                .productId(Long.valueOf(334562)).build();

        price = Price.builder()
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
                .priority(1)
                .priceList(1)
                .price(BigDecimal.valueOf(56.99))
                .productId(Long.valueOf(345566))
                .currency("EUR")
                .build();
    }

    @Test
    void givenPriceWhenPriceFound()
    {
        Mockito.when(priceRepository.findRateToApply(filter))
                .thenReturn(Optional.of(price));
        Price priceFound = priceApplicatorService
                .findRateToApply(filter);
        assertThat(priceFound.getPrice()).isEqualTo(price.getPrice());

    }

    @Test
    void givenErrorWhenPriceNotFound()
    {
        Mockito.when(priceRepository.findRateToApply(filter))
                .thenReturn(Optional.empty());
        Throwable exception = assertThrows(
                PriceNotFoundException.class, () -> {
                    Price priceFound = priceApplicatorService
                            .findRateToApply(filter);
                }
        );
        assertEquals("Price not Found", exception.getMessage());
    }


}
