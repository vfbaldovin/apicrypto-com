package com.web.apicrypto.controller;

import com.web.apicrypto.model.dto.*;
import com.web.apicrypto.service.AuthService;
import com.web.apicrypto.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@RequestBody RegisterRequest registerRequest) {
        ApiResponse authResponse = authService.signup(registerRequest);
        return new ResponseEntity<>(authResponse, OK);
    }

    @PostMapping("/accountVerification")
    public ResponseEntity<ApiResponse> verifyAccount(@RequestBody String token) {
        ApiResponse apiResponse = authService.verifyAccount(token);
        return new ResponseEntity<>(apiResponse, OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }

    @PostMapping("/reset")
    public ResponseEntity<ApiResponse> resetPassword(@RequestBody String email) {
        ApiResponse apiResponse = authService.resetPassword(email);
        return new ResponseEntity<>(apiResponse, OK);
    }
}
