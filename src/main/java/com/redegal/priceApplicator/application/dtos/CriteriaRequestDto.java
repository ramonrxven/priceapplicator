package com.redegal.priceApplicator.application.dtos;

import lombok.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CriteriaRequestDto {

    private Integer brandId;

    private LocalDateTime applicationDate;

    private Long productId;

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = LocalDateTime.parse(applicationDate);
    }
}
