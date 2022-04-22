package com.web.apicrypto.api.chart.model;

import com.web.apicrypto.api.model.StatusResponse;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OhlcvResponse {
    private StatusResponse status;
    private Data data;
}
