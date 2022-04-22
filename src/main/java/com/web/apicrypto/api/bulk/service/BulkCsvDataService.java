package com.web.apicrypto.api.bulk.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.internal.MongoClientImpl;
import com.web.apicrypto.webapp.exceptions.ApiCryptoException;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BulkCsvDataService {

    public Object getCsvData() {
        String connectionString ="mongodb://alex:DoamnaMurzea5513@178.18.242.210:27017/crypto_db?authSource=crypto_db";
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {


            MongoDatabase cryptoDb = mongoClient.getDatabase("crypto_db");
            MongoCollection<Document> collection = cryptoDb.getCollection("cryptocurrency_ohlcv_historical_active_14_04_22");
//            ohlcvHistoricalActive.find().forEach(System.out::println);
            System.out.println("collection.find().first() = " + collection.find().first());
            return null;
        }
    }
}
