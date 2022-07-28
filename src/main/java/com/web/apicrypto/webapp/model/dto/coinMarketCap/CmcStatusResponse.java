package com.web.apicrypto.webapp.model.dto.coinMarketCap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CmcStatusResponse {
    private String timestamp;
    private int error_code;
    private String error_message;
}