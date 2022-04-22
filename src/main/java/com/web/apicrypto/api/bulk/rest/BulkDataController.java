package com.web.apicrypto.api.bulk.rest;

import com.web.apicrypto.api.bulk.service.BulkCsvDataService;
import com.web.apicrypto.api.chart.model.ChartRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chart")
@AllArgsConstructor
public class BulkDataController {

    BulkCsvDataService bulkCsvDataService;

    @GetMapping(value = "/bulk/csv")
    public Object getOhlcv() {

        return  bulkCsvDataService.getCsvData();
    }
}
