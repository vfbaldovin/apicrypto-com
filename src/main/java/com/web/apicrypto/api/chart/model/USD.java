package com.web.apicrypto.api.chart.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class USD {
    private double open;
    private double high;
    private double low;
    private double close;
    private double volume;
    private double market_cap;
    private String timestamp;
}
