package com.web.apicrypto.api.chart.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Data {
    private Long id;
    private String name;
    private String symbol;
    private List<Quotes> quotes;
}
