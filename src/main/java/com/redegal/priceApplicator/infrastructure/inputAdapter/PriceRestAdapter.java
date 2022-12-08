package com.redegal.priceApplicator.infrastructure.inputAdapter;

import com.redegal.priceApplicator.application.dtos.CriteriaRequestDto;
import com.redegal.priceApplicator.application.dtos.PriceSeletedResponseDto;
import com.redegal.priceApplicator.application.services.PriceApplicatorService;
import com.redegal.priceApplicator.infrastructure.inputAdapter.mappers.PriceRestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class PriceRestAdapter implements PriceRestAdapterApi{
    @Autowired
    PriceApplicatorService priceApplicatorService;

    @Autowired
    PriceRestMapper priceRestMapper;
    public ResponseEntity<PriceSeletedResponseDto> getPriceToApply(CriteriaRequestDto filter){
        return new ResponseEntity<>(priceRestMapper.toPriceSelectedResponseDto(
                priceApplicatorService.findRateToApply(filter))
                , HttpStatus.OK);
    }
}
