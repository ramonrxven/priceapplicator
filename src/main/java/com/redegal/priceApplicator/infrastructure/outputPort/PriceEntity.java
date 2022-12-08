package com.redegal.priceApplicator.infrastructure.outputPort;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prices")
public class PriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priceList;
    private Long productId;
    private Integer priority;
    private BigDecimal price;
    private String currency;
}
