package com.web.apicrypto.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MailRequest {
    private String name;
    private String email;
    private String subject;
    private String message;
}

