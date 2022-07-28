package com.web.apicrypto.webapp.model.dto.coinMarketCap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CmcMapResponse {
    private CmcStatusResponse status;
    private List<CmcMapDto> data;
}