package com.web.apicrypto.api.chart.service;

import com.fasterxml.jackson.databind.JsonNode;
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
import java.util.*;

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
        chartResponses.add(timestamp * 1000);
        chartResponses.add(roundDouble(usd.getOpen()));
        chartResponses.add(roundDouble(usd.getHigh()));
        chartResponses.add(roundDouble(usd.getLow()));
        chartResponses.add(roundDouble(usd.getClose()));

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

    public TickerResponse getTicker(String id) {
        UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(ClientEndpoint.QUOTES_LATEST);
        url.queryParam("id", id);


        try {
            JsonNode quotesRespone = restTemplate.getForObject(url.build().toUriString(), JsonNode.class);
            if (quotesRespone == null) {
                return new TickerResponse();
            }
            String name = quotesRespone.get("data").get("1").get("name").asText("");
            String symbol = quotesRespone.get("data").get("1").get("symbol").asText("");
            double price = quotesRespone.get("data").get("1").get("quote").get("USD").get("price").asDouble(0);
            double percentChange24H = quotesRespone.get("data").get("1").get("quote").get("USD").get("percent_change_24h").asDouble(0);

            return TickerResponse.builder()
                    .name(name)
                    .symbol(symbol)
                    .percentChange24H(roundDouble(percentChange24H))
                    .price(roundDouble(price))
                    .build();

        } catch (HttpClientErrorException e) {
            log.info("Bad Url request " + e.getMessage());
            throw new ApiCryptoException("Bad Url request");
        }
    }

    public byte[] getLogo(String size, String id) {
        if (size == null || !Arrays.asList("16x16", "32x32", "64x64", "128x128").contains(size)) {
            throw new ApiCryptoException("The size must be 16x16, 32x32, 64x64 or 128x128");
        }
        UriComponentsBuilder url = UriComponentsBuilder.fromHttpUrl(ClientEndpoint.LOGO);

        url.pathSegment(size).pathSegment(id);

        try {
            return restTemplate.getForObject(url.build().toUriString() + ".png", byte[].class);
        } catch (HttpClientErrorException e) {
            if (e.getRawStatusCode() == 403) {
                throw new ApiCryptoException("Image not found");
            }
            throw new ApiCryptoException("Invalid request");
        }
    }

    private Double roundDouble(Double value) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(value));
    }

}
