package com.redegal.priceApplicator.infrastructure.outputAdapter;

import com.redegal.priceApplicator.application.dtos.CriteriaRequestDto;
import com.redegal.priceApplicator.application.repositories.PriceRepository;
import com.redegal.priceApplicator.domain.models.Price;
import com.redegal.priceApplicator.infrastructure.outputPort.PriceEntity;
import com.redegal.priceApplicator.infrastructure.outputPort.PriceEntityRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PriceEntityH2Repository implements PriceRepository {

    @Autowired
    PriceEntityRepository priceEntityRepository;

    private final PricePersistenseMapper pricePersistenseMapper = Mappers.getMapper(PricePersistenseMapper.class);
    @Override
    public Optional<Price> findRateToApply(CriteriaRequestDto filter) {
        List<PriceEntity> prices = priceEntityRepository.findRateToApply(filter.getProductId(),
                filter.getBrandId(), filter.getApplicationDate());
        if ( prices.isEmpty() )
            return Optional.empty();
        return Optional.of(pricePersistenseMapper.toPrice(prices.get(0)));
    }
}
