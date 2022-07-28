package com.web.apicrypto.webapp.model.dto.coinMarketCap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CmcMapDto {
    private Long id;
    private String name;
    private String symbol;
    private String slug;
    private Long rank;
    private Boolean is_active;
    private String first_historical_data;
    private String last_historical_data;
}
