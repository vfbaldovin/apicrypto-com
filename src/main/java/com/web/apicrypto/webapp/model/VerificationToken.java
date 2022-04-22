package com.web.apicrypto.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("token")
public class VerificationToken {
    @Id
    private String id;
    @Field("token")
    private String token;
    @DBRef
    private User user;
    @Field("expiryDate")
    private LocalDateTime expiryDate;
}
