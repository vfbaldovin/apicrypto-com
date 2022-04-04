package com.web.apicrypto.chart.rest;

import com.web.apicrypto.chart.model.ChartRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chart")
@AllArgsConstructor
public class ChartController {

//    private final ChartService chartService;
//
//    @GetMapping(value = "/historical")
//    public ChartResponse getHistorical(@RequestBody ChartRequest chartRequest) {
//        return chartService.getQuotesHistorical(chartRequest);
//    }
}
