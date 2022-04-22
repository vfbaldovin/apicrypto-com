package com.web.apicrypto.api.chart.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quote {
    @JsonProperty("USD")
    private USD USD;
}
