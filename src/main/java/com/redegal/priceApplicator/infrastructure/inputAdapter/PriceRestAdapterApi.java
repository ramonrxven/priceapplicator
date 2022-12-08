package com.redegal.priceApplicator.infrastructure.inputAdapter;

import com.redegal.priceApplicator.application.dtos.CriteriaRequestDto;
import com.redegal.priceApplicator.application.dtos.PriceSeletedResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


public interface PriceRestAdapterApi {

    @Operation(summary = "Obtener el precio a aplicar para un día a una hora " +
            "determinada.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Price Found",
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation = PriceSeletedResponseDto.class))}),
            @ApiResponse(responseCode = "Invalid Parameters",
            content = @Content),
            @ApiResponse(responseCode = "404",
            description = "Price not Found",
            content = @Content)
    })
    @GetMapping(value = "/pricetoapply")
    public ResponseEntity<PriceSeletedResponseDto>
        getPriceToApply(CriteriaRequestDto filter);
}
