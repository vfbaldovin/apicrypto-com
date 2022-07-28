package com.web.apicrypto.webapp.service;

import com.web.apicrypto.webapp.exceptions.CmcClientException;
import com.web.apicrypto.webapp.model.CryptoMap;
import com.web.apicrypto.webapp.model.constants.CmcEndpoints;
import com.web.apicrypto.webapp.model.dto.coinMarketCap.CmcMapDto;
import com.web.apicrypto.webapp.model.dto.coinMarketCap.CmcMapResponse;
import com.web.apicrypto.webapp.repository.CryptoMapRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private final CryptoMapRepository repository;

    public ScheduledTasks(CryptoMapRepository repository) {
        this.repository = repository;
    }

    @Scheduled(fixedRate = 1000 * 3600 * 12) //12 hours
    public void updateMap() {
        try {
            CmcMapResponse mapResponse = getApiResponse(CmcEndpoints.MAP);

            List<CryptoMap> mapList = mapResponse.getData().stream().map(this::mapCryptomap).collect(Collectors.toList());

            repository.saveAll(mapList);
            System.out.println("map updated successfully");

        } catch (Exception e) {
            log.error("Error occurred when updating the MAP: {}", e.getMessage());
        }
    }

    private CryptoMap mapCryptomap(CmcMapDto mapDto) {
        return CryptoMap.builder()
                ._id(mapDto.getId())
                .name(mapDto.getName())
                .symbol(mapDto.getSymbol())
                .slug(mapDto.getSlug())
                .rank(mapDto.getRank())
                .is_active(mapDto.getIs_active())
                .first_historical_data(mapDto.getFirst_historical_data())
                .last_historical_data(mapDto.getLast_historical_data())
                .build();
    }

    private CmcMapResponse getApiResponse(String url) throws JSONException {
        CmcMapResponse apiResponse;
        try {
            RestTemplate restTemplate = new RestTemplate();
            apiResponse = restTemplate.getForObject(url, CmcMapResponse.class);
        } catch (HttpClientErrorException e) {
            System.out.println("e = " + e.getResponseBodyAsString());
            String responseString =  e.getResponseBodyAsString();
            JSONObject jsonObject = new JSONObject(responseString);
            JSONObject jsonObjectStatus = new JSONObject(jsonObject.getString("status"));
            System.out.println("jsonObject = " + jsonObject);
            log.info("Bad Url request " + e.getMessage());
            throw new JSONException(jsonObjectStatus.getString("error_message"));
        }
        if (apiResponse == null) {
            log.info("apiResponse is null");
            throw new CmcClientException("Something went wrong.");
        }
        return apiResponse;
    }
}
