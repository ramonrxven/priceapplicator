package com.redegal.priceApplicator.infrastructure.outputAdapter;

import com.redegal.priceApplicator.domain.models.Price;
import com.redegal.priceApplicator.infrastructure.outputPort.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PricePersistenseMapper {

    Price toPrice(PriceEntity priceEntity);
    Iterable<Price> toPrices(Iterable<Price> pricesEntities);
}

