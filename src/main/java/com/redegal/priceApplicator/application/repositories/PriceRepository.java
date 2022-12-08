package com.redegal.priceApplicator.application.repositories;

import com.redegal.priceApplicator.application.dtos.CriteriaRequestDto;
import com.redegal.priceApplicator.domain.models.Price;

import java.util.Optional;

public interface PriceRepository {
    public Optional<Price> findRateToApply(CriteriaRequestDto filter);
}
