package com.redegal.priceApplicator.infrastructure.outputPort;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceEntityRepository extends JpaRepository<PriceEntity, Long> {
    @Query(value = "SELECT * FROM prices p WHERE :applicationDate >= p.START_DATE AND :applicationDate <= p.END_DATE"
            +" AND p.PRODUCT_ID = :productId"
            +" AND p.BRAND_ID = :brandId"
            +" ORDER BY p.PRIORITY DESC"
            +" LIMIT 1",
            nativeQuery = true)
    List<PriceEntity> findRateToApply(@Param("productId")Long productId,
                                      @Param("brandId") Integer brandId,
                                      @Param("applicationDate") LocalDateTime applicationDate);

}
