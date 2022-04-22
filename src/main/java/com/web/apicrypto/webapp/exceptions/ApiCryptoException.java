package com.web.apicrypto.webapp.exceptions;


public class    ApiCryptoException extends RuntimeException{

    public ApiCryptoException(String message) {
        super(message);
    }

    public ApiCryptoException(String message, Exception exception) {
        super(message, exception);
    }
}

