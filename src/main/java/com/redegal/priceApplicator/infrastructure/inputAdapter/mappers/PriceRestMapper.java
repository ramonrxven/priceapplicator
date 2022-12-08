package com.redegal.priceApplicator.infrastructure.inputAdapter.mappers;

import com.redegal.priceApplicator.application.dtos.PriceSeletedResponseDto;
import com.redegal.priceApplicator.domain.models.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PriceRestMapper {
    @Mappings({
            @Mapping(source = "brandId", target = "brandId"),
            @Mapping(source = "productId", target = "productId"),
            @Mapping(source = "price", target = "price"),
            @Mapping(source = "priceList", target = "priceList"),
            @Mapping(source = "applicationDate", target = "applicationDate")
    }
    )
    PriceSeletedResponseDto toPriceSelectedResponseDto(Price price);
}
