//package com.web.apicrypto.utils;
//
//import lombok.extern.java.Log;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.lang.reflect.Field;
//import java.util.logging.Level;
//
//@Slf4j
//public class AppUtils {
//
//    public static UriComponentsBuilder enrichWithParameters(Object object, UriComponentsBuilder url) {
//
//        try {
//            for (Field field : object.getClass().getDeclaredFields()) {
//                url.queryParam(field.getName(), field.get(object));
//            }
//
//        } catch (IllegalAccessException e) {
//            log.error("Object access field error: {}", e.getMessage());
//        }
//        return url;
//    }
//
//}
