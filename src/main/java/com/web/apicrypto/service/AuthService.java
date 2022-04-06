package com.web.apicrypto.service;

import com.web.apicrypto.exceptions.error.ApiHttpStatus;
import com.web.apicrypto.exceptions.ApiCryptoException;
import com.web.apicrypto.model.NotificationEmail;
import com.web.apicrypto.model.User;
import com.web.apicrypto.model.VerificationToken;
import com.web.apicrypto.model.dto.*;
import com.web.apicrypto.repository.UserRepository;
import com.web.apicrypto.repository.VerificationTokenRepository;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

import static com.web.apicrypto.model.constants.AppConstants.URL;

@Service
//@AllArgsConstructor
@Transactional
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;
//    @Value("${application.url}")
//    private String URL;

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, VerificationTokenRepository verificationTokenRepository, MailService mailService, AuthenticationManager authenticationManager, JwtProvider jwtProvider, RefreshTokenService refreshTokenService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.mailService = mailService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.refreshTokenService = refreshTokenService;
    }

    public ApiResponse signup(RegisterRequest registerRequest) {
        Optional<User> userOptional = userRepository.findByUsername(registerRequest.getUsername());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (!user.isEnabled()) {
                resendMailActivation(user);
                throw new ApiCryptoException(ApiHttpStatus.RESEND_ACTIVATION_MAIL.getMessage());
            }
            throw new ApiCryptoException(ApiHttpStatus.EMAIL_TAKEN.getMessage());
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(LocalDateTime.now(ZoneOffset.UTC));
        user.setEnabled(false);

        userRepository.save(user);

        String token = generateVerificationToken(user);

        mailService.sendMail(new NotificationEmail("Welcome to apicrypto.com!", user.getUsername(),
                "<html><body><div>Hello,</div><br><div>Congratulations! You have taken your first step towards your " +
                        "project and we are excited to share with you relevant cryptocurrencies data feeds.\n</div><br>" +
                        "<div>Access the following link to activate your account:</div>" + "<br><div><a href = \"" + URL + "accountVerification/"+token+"\">" + URL +"accountVerification</a></div>" + "<br><div>If something is not clear, let us know how we can help you.</div><br><div>Best Regards,</div><a href=\""+ URL +"\">Api Crypto Team</a></body></html>"));

        System.out.println("Activation link = " + (URL + "/api/auth/accountVerification/" + token));

        return ApiResponse.build(ApiHttpStatus.SUCCESS);
    }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(ApiHttpStatus.EMAIL_NOT_FOUND.getMessage()));
    }

    private ApiResponse fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ApiCryptoException(ApiHttpStatus.EMAIL_NOT_FOUND.getMessage()));
        user.setEnabled(true);
        userRepository.save(user);

        return ApiResponse.build(ApiHttpStatus.SUCCESS);
    }

    public void resendMailActivation(User user) {
        String token = generateVerificationToken(user);
        //TODO: change to send Email to current user
        System.out.println("Activation link = " + (URL + "/api/auth/accountVerification/" + token));

        mailService.sendMail(new NotificationEmail("Welcome to apicrypto.com!",
                user.getUsername(), "<html><body><div>Hello,</div><br><div>Congratulations! You have taken your first step towards your project and we are excited to share with you relevant cryptocurrencies data feeds.\n</div><br>" +
                "<div>Access the following link to activate your account:</div>" +
                "<br><div><a href = \"" + URL + "accountVerification/"+token+"\">" + URL +"accountVerification</a></div>" +
                "<br><div>If something is not clear, let us know how we can help you.</div><br><div>Best Regards,</div><a href=\""+ URL +"\">Api Crypto Team</a></body></html>"));
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        LocalDateTime timestamp = LocalDateTime.now().plus(1, ChronoUnit.HOURS);
        verificationToken.setExpiryDate(timestamp);
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public ApiResponse verifyAccount(String token) {
        Optional<VerificationToken> verificationTokenOptional = verificationTokenRepository.findByToken(token);
        VerificationToken verificationToken = verificationTokenOptional.orElseThrow(() -> new ApiCryptoException(ApiHttpStatus.INVALID_TOKEN.getMessage()));
        return fetchUserAndEnable(verificationToken);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate;
        userRepository.findByUsername(loginRequest.getEmail()).orElseThrow(() -> new ApiCryptoException(ApiHttpStatus.EMAIL_NOT_FOUND.getMessage()));
        try {
             authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                    loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new ApiCryptoException(ApiHttpStatus.INVALID_PASSWORD.getMessage());
        } catch (DisabledException e) {
            throw new ApiCryptoException(ApiHttpStatus.USER_NOT_ENABLED.getMessage());
        }

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(loginRequest.getEmail())
                .build();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }

    public ApiResponse resetPassword(String email) {
        User user = userRepository.findByUsername(email).orElseThrow(() -> new ApiCryptoException(ApiHttpStatus.EMAIL_NOT_FOUND.getMessage()));
        if (!user.isEnabled()) {
            throw new ApiCryptoException(ApiHttpStatus.USER_NOT_ENABLED.getMessage());
        }

        String token = generateVerificationToken(user);

        mailService.sendMail(new NotificationEmail("Recover password",
                user.getUsername(), "<html><body><div>Hello,</div><br><div>Thank you for using apicrypto.com!\n</div><br>" +
                "<div>Please access the following link to reset your password:</div>" +
                "<br><div><a href = \"" + URL + "reset/"+token+"\">" + URL +"reset</a></div>" +
                "<br><div>If something is not clear, let us know how we can help you.</div><br><div>Best Regards,</div><a href=\""+ URL +"\">Api Crypto Team</a></body></html>"));
        return ApiResponse.build(ApiHttpStatus.SUCCESS);
    }

    public ApiResponse changePassword(ChangePasswordDto changePasswordDto) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(changePasswordDto.getToken())
                .orElseThrow(() -> new ApiCryptoException(ApiHttpStatus.INVALID_TOKEN.getMessage()));
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ApiCryptoException(ApiHttpStatus.EMAIL_NOT_FOUND.getMessage()));
        user.setPassword(passwordEncoder.encode(changePasswordDto.getPassword()));
        userRepository.save(user);
        return ApiResponse.build(ApiHttpStatus.SUCCESS);
    }
}
