package com.web.apicrypto.chart.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChartRequest {
    private String id;
    private String time_start;
    private String time_end;
    private String count;
    private String interval;
    private String convert;
    private String convert_id;
}
