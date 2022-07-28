package com.web.apicrypto.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("crypto_map")
public class CryptoMap {
    @Id
    private Long _id;
    private String name;
    private String symbol;
    private String slug;
    private Long rank;
    private Boolean is_active;
    private String first_historical_data;
    private String last_historical_data;
}
