package com.web.apicrypto.webapp.exceptions.handler;

import com.web.apicrypto.webapp.exceptions.ApiCryptoException;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

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

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({BadCredentialsException.class, DisabledException.class, UsernameNotFoundException.class})
    @ResponseBody
    public String loginHandler(ApiCryptoException apiCryptoException) {
        log.info("ApiCryptoException --- " + apiCryptoException.getMessage());
        return apiCryptoException.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseBody
    public String chartHandler(ApiCryptoException apiCryptoException) {
        log.info("ApiCryptoException --- " + apiCryptoException.getMessage());
        return apiCryptoException.getMessage();
    }
}
