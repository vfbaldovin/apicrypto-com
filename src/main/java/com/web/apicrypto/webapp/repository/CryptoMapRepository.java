package com.web.apicrypto.webapp.repository;

import com.web.apicrypto.webapp.model.CryptoMap;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface CryptoMapRepository extends MongoRepository<CryptoMap, Long> {
    @Cacheable("cryptoMap")
    List<CryptoMap> findAll();
}
