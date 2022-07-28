package com.web.apicrypto.webapp.exceptions;

public class CmcClientException extends RuntimeException {
    public CmcClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public CmcClientException(String message) {
        super(message);
    }
}
