package com.redegal.priceApplicator.application.services;

import com.redegal.priceApplicator.application.dtos.CriteriaRequestDto;
import com.redegal.priceApplicator.domain.models.Price;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface PriceApplicatorService {
    public Price findRateToApply(CriteriaRequestDto filter);
}
