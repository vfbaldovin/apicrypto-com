package com.web.apicrypto.exceptions.handler;

import com.web.apicrypto.exceptions.ApiCryptoException;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Log
@ControllerAdvice
public class HttpErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ApiCryptoException.class)
    @ResponseBody
    public String restHandler(ApiCryptoException apiCryptoException) {
        log.info("ApiCryptoException --- " + apiCryptoException.getMessage());
        return apiCryptoException.getMessage();
    }
}
