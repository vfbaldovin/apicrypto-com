package com.web.apicrypto.api.chart.rest;

import com.web.apicrypto.api.chart.model.ChartRequest;
import com.web.apicrypto.api.chart.model.ChartResponse;
import com.web.apicrypto.api.chart.model.TickerResponse;
import com.web.apicrypto.api.chart.service.ChartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ChartController {

    private final ChartService chartService;

    @GetMapping(value = "/chart/ohlcv")
    public List<List<Object>> getOhlcv(@RequestBody(required = false) ChartRequest chartRequest) {
        return chartService.getQuotesHistorical(chartRequest);
    }

    @GetMapping(value = "/ticker/{id}")
    public TickerResponse getOhlcv(@PathVariable String id) {
        return chartService.getTicker(id);
    }

    @GetMapping(value = "/logo/{size}/{id}", produces = "image/png")
    public byte[] getLogo(@PathVariable String size, @PathVariable String id) {
        return chartService.getLogo(size, id);
    }

//    @GetMapping(value = "/quotes")
//    public ChartResponse getQuotes(@RequestBody @NotNull ChartRequest chartRequest) {
//        return chartService.getQuotesHistorical(chartRequest);
//    }
}
