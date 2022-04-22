package com.web.apicrypto.api.chart.service;

import com.web.apicrypto.api.chart.model.*;
import com.web.apicrypto.api.utils.UrlBuilder;
import com.web.apicrypto.webapp.exceptions.ApiCryptoException;
import com.web.apicrypto.webapp.exceptions.error.ApiHttpStatus;
import com.web.apicrypto.api.model.constants.ClientEndpoint;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ChartService {

    private final RestTemplate restTemplate;

    public List<List<Object>> getQuotesHistorical(ChartRequest chartRequest) {
        UriComponentsBuilder url = UrlBuilder.buildUrl(ClientEndpoint.OHLCV_HISTORICAL, chartRequest);

        Date nowDate = Date.from(ZonedDateTime.now().plusDays(1).toInstant());
        Date oneMonthAgoDate = Date.from(ZonedDateTime.now().minusYears(1).toInstant());


        url.queryParam("id", 1);

        url.queryParam("time_start", oneMonthAgoDate.getTime());
        url.queryParam("time_end", nowDate.getTime());

        return mapToChartResponse(getResponse(url));
    }

    private List<List<Object>> mapToChartResponse(OhlcvResponse ohlcvResponse) {
        List<List<Object>> response = new ArrayList<>();

        ohlcvResponse.getData().getQuotes().forEach(quote -> {
            List<Object> chartResponses = getChartResponses(quote);
            response.add(chartResponses);
        });

        return response;
    }

    private List<Object> getChartResponses(Quotes quote) {
        List<Object> chartResponses = new ArrayList<>();
        USD usd = quote.getQuote().getUSD();
        long timestamp = Instant.parse(usd.getTimestamp()).getEpochSecond();
        DecimalFormat df = new DecimalFormat("#.##");
        chartResponses.add(timestamp * 1000);
        chartResponses.add(Double.valueOf(df.format(usd.getOpen())));
        chartResponses.add(Double.valueOf(df.format(usd.getHigh())));
        chartResponses.add(Double.valueOf(df.format(usd.getLow())));
        chartResponses.add(Double.valueOf(df.format(usd.getClose())));

        return chartResponses;
    }

    private OhlcvResponse getResponse(UriComponentsBuilder url) {
        try {
            log.info("URL: {}", url.build());

            return restTemplate.getForObject(url.build().toUriString(), OhlcvResponse.class);
        } catch (HttpClientErrorException e) {
            log.error("Error on calling: {}, error: {}", url.build(), e.getMessage());
            throw new ApiCryptoException(ApiHttpStatus.BAD_REQUEST.getMessage());
        } catch (Exception e){
            throw new ApiCryptoException(ApiHttpStatus.UNKNOWN_EXCEPTION.getMessage());
        }
    }
}
