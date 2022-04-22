package com.web.apicrypto.api.chart.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChartResponse {
    private String timestamp;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
}
