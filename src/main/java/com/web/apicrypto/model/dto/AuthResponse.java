package com.web.apicrypto.model.dto;

import com.web.apicrypto.error.ErrorConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private int errorCode;
    private String message;

    public static AuthResponse build(ErrorConstants errorConstants) {
        return AuthResponse.builder()
                .errorCode(errorConstants.getCode())
                .message(errorConstants.getMessage())
                .build();
    }
}
