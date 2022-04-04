package com.web.apicrypto.exceptions.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorConstants {
    SUCCESS(0, "Success"),
    EMAIL_TAKEN(1, "Email address already in use"),
    EMAIL_NOT_FOUND(2, "Email address: %s, does not exist"),
    PASSWORD_COMPLIANCE(3, "Passwords don't match"),
    RESEND_ACTIVATION_MAIL(4, "The email is already registered. An activation email has been sent back, please check your inbox and also the spam folder."),
    USER_NOT_ENABLED(5, "Your account is not enabled. Please register again");

    private final int code;
    private final String message;
}
