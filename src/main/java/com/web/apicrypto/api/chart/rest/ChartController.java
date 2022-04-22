package com.web.apicrypto.api.chart.rest;

import com.web.apicrypto.api.chart.model.ChartRequest;
import com.web.apicrypto.api.chart.model.ChartResponse;
import com.web.apicrypto.api.chart.service.ChartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/chart")
@AllArgsConstructor
public class ChartController {

    private final ChartService chartService;

    @GetMapping(value = "/ohlcv")
    public List<List<Object>> getOhlcv(@RequestBody(required = false) ChartRequest chartRequest) {
        return chartService.getQuotesHistorical(chartRequest);
    }

//    @GetMapping(value = "/quotes")
//    public ChartResponse getQuotes(@RequestBody @NotNull ChartRequest chartRequest) {
//        return chartService.getQuotesHistorical(chartRequest);
//    }
}
