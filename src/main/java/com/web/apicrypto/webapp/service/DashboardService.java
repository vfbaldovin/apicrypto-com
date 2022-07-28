package com.web.apicrypto.webapp.service;

import com.web.apicrypto.webapp.model.CryptoMap;
import com.web.apicrypto.webapp.repository.CryptoMapRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class DashboardService {

    private final CryptoMapRepository repository;

    public List<String> getCryptoNames(String name) {
        if (!StringUtils.hasLength(name) || name.length() < 2) {
            return Collections.emptyList();
        }
        return this.repository.findAll().stream()
                .map(CryptoMap::getName)
                .filter((value) -> value.toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }
}
