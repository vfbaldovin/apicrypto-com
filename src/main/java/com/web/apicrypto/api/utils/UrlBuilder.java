package com.web.apicrypto.api.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Field;

@Slf4j
public class UrlBuilder extends UriComponentsBuilder {

    public static UriComponentsBuilder buildUrl(String httpUrl, Object parameters) {
        UriComponentsBuilder url = UrlBuilder.fromHttpUrl(httpUrl);
        if (parameters != null) {
            enrichWithParameters(parameters, url);
        }
        return url;
    }

    private static void enrichWithParameters(Object parameters, UriComponentsBuilder url) {
        try {
            for (Field field : parameters.getClass().getDeclaredFields()) {
                if (field.get(parameters) != null && !field.get(parameters).equals(""))
                url.queryParam(field.getName(), field.get(parameters));
            }
        } catch (IllegalAccessException e) {
            log.error("Object access field error: {}", e.getMessage());
        }
    }
}
