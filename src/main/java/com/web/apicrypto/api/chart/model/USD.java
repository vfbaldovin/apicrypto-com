package com.web.apicrypto.api.chart.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class USD {
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Double volume;
    private Double market_cap;
    private String timestamp;
}
