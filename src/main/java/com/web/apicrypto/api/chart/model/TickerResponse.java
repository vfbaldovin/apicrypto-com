package com.web.apicrypto.api.chart.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TickerResponse {
    private String name;
    private String symbol;
    private double price;
    private double percentChange24H;
}
