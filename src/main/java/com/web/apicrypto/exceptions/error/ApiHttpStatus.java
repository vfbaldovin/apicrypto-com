package com.web.apicrypto.exceptions.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApiHttpStatus {
    SUCCESS(0, "Success"),
    EMAIL_TAKEN(1, "Email address already in use"),
    EMAIL_NOT_FOUND(2, "Email address is not registered"),
    PASSWORD_COMPLIANCE(3, "Passwords don't match"),
    RESEND_ACTIVATION_MAIL(4, "The email is already registered. An activation email has been sent back, please check your inbox and also the spam folder."),
    USER_NOT_ENABLED(5, "Your account is not enabled. Please register again"),
    LOGIN_FAILED(6, "There was a problem logging in. Check your email and password or create an account.");

    private final int code;
    private final String message;
}
