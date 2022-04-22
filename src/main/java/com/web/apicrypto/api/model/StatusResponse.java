package com.web.apicrypto.api.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusResponse {
    private String timestamp;
    private int error_code;
    private String error_message;
    private Long elapsed;
    private int credit_count;
    private String notice;
}
