package com.web.apicrypto.api.chart.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quotes {
    private String time_open;
    private String time_close;
    private String time_high;
    private String time_low;
    private Quote quote;
}
