package com.redegal.priceApplicator.AdaptersUnitTest;

import static org.assertj.core.api.Assertions.assertThat;
import com.redegal.priceApplicator.application.dtos.CriteriaRequestDto;
import com.redegal.priceApplicator.domain.models.Price;
import com.redegal.priceApplicator.infrastructure.outputAdapter.PriceEntityH2Repository;
import com.redegal.priceApplicator.infrastructure.outputAdapter.PricePersistenseMapper;
import com.redegal.priceApplicator.infrastructure.outputPort.PriceEntity;
import com.redegal.priceApplicator.infrastructure.outputPort.PriceEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PriceEntityH2RepositoryUnitTest {

    @InjectMocks
    PriceEntityH2Repository priceEntityH2Repository;

    @Autowired
    CriteriaRequestDto criteriaRequestDto;

    PricePersistenseMapper pricePersistenseMapper =
            Mockito.mock(PricePersistenseMapper.class);

    PriceEntityRepository priceEntityRepository =
            Mockito.mock(PriceEntityRepository.class);

    Price price;

    PriceEntity priceEntity;

    @BeforeEach
    void setUp()
    {
        price = Price.builder()
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
                .priority(1)
                .priceList(1)
                .price(BigDecimal.valueOf(56.99))
                .productId(Long.valueOf(345566))
                .currency("EUR")
                .build();
        priceEntity = PriceEntity.builder()
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
                .priority(1)
                .priceList(1)
                .price(BigDecimal.valueOf(56.99))
                .productId(Long.valueOf(345566))
                .currency("EUR")
                .build();
        criteriaRequestDto = CriteriaRequestDto.builder()
                .applicationDate(LocalDateTime.parse("2020-02-14T10:00:00"))
                .brandId(1)
                .productId(Long.valueOf(334562)).build();
        Mockito.when(pricePersistenseMapper.toPrice(priceEntity))
                .thenReturn(price);
    }

    @Test
    void givenPriceWhenPriceFound()
    {
        Mockito.when(priceEntityRepository.findRateToApply(
                        criteriaRequestDto.getProductId(),
                        criteriaRequestDto.getBrandId(),
                        criteriaRequestDto.getApplicationDate()))
                .thenReturn(List.of(priceEntity));
        Optional<Price> priceFound = priceEntityH2Repository
                .findRateToApply(criteriaRequestDto);
        assertThat(priceFound.get().getEndDate()).isEqualTo(price.getEndDate());
    }

    @Test
    void givenEmptyPriceWhenNotFound()
    {
        Mockito.when(priceEntityRepository.findRateToApply(
                        criteriaRequestDto.getProductId(),
                        criteriaRequestDto.getBrandId(),
                        criteriaRequestDto.getApplicationDate()))
                .thenReturn(List.of());
        Optional<Price> priceFound = priceEntityH2Repository
                .findRateToApply(criteriaRequestDto);
        assertThat(priceFound.isEmpty()).isEqualTo(true);


    }
}
