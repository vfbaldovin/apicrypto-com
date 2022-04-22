package com.web.apicrypto.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("refresh_token")
public class RefreshToken {
    @Id
    private String id;
    @Field("token")
    private String token;
    @Field("createdDate")
    private Instant createdDate;
}
