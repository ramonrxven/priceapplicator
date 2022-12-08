package com.redegal.priceApplicator.application.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceSeletedResponseDto {
    private Integer brandId;
    private LocalDateTime applicationDate;
    private Integer priceList;
    private Long productId;
    private BigDecimal price;
}
