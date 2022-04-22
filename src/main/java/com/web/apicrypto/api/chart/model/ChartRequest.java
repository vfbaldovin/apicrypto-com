package com.web.apicrypto.api.chart.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChartRequest {
    public String id;
    public String time_start;
    public String time_end;
    public String count;
    public String interval;
    public String convert;
}
