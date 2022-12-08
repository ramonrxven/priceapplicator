package com.redegal.priceApplicator.PriceRestAdapterTest;

import static org.assertj.core.api.Assertions.assertThat;
import com.redegal.priceApplicator.application.dtos.CriteriaRequestDto;
import com.redegal.priceApplicator.application.dtos.PriceSeletedResponseDto;
import com.redegal.priceApplicator.application.services.PriceApplicatorService;
import com.redegal.priceApplicator.domain.models.Price;
import com.redegal.priceApplicator.infrastructure.inputAdapter.PriceRestAdapter;
import com.redegal.priceApplicator.infrastructure.inputAdapter.mappers.PriceRestMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceRestAdapterUnitTest {

    @InjectMocks
    PriceRestAdapter priceRestAdapter;

    PriceApplicatorService priceApplicatorService =
            Mockito.mock(PriceApplicatorService.class);
    PriceRestMapper priceRestMapper = Mockito.mock(PriceRestMapper.class);

    Price price;
    PriceSeletedResponseDto priceSeletedResponseDto;
    @BeforeEach
    void Setup()
    {
        price = Price.builder().build();
        priceSeletedResponseDto =
                PriceSeletedResponseDto.builder()
                        .priceList(1)
                        .applicationDate(LocalDateTime.parse("2020-06-14T16:00:00"))
                        .price(BigDecimal.valueOf(40.00))
                        .brandId(1)
                        .productId(Long.valueOf(35433))
                        .build();
    }

    @Test
    void shouldGetPriceSelected()
    {
        CriteriaRequestDto criteriaRequestDto = new CriteriaRequestDto();
        when(priceRestMapper.toPriceSelectedResponseDto(price))
                .thenReturn(priceSeletedResponseDto);
        when(priceApplicatorService.findRateToApply(criteriaRequestDto))
                .thenReturn(price);
        ResponseEntity<PriceSeletedResponseDto> resposeEntityPrice = this.priceRestAdapter
            .getPriceToApply(criteriaRequestDto);
        assertThat(resposeEntityPrice.getStatusCodeValue()).isEqualTo(200);
        assertThat(resposeEntityPrice.getBody())
                .isEqualTo(priceSeletedResponseDto);
    }
}
