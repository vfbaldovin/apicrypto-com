package com.web.apicrypto.model.dto;

import com.web.apicrypto.exceptions.error.ApiHttpStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {
    private int errorCode;
    private String message;

    public static ApiResponse build(ApiHttpStatus errorConstants) {
        return ApiResponse.builder()
                .errorCode(errorConstants.getCode())
                .message(errorConstants.getMessage())
                .build();
    }
}
