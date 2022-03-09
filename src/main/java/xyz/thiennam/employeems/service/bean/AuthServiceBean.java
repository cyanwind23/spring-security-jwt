package xyz.thiennam.employeems.service.bean;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.thiennam.employeems.entity.NotificationEmail;
import xyz.thiennam.employeems.entity.User;
import xyz.thiennam.employeems.entity.dto.*;
import xyz.thiennam.employeems.jwt.JwtProvider;
import xyz.thiennam.employeems.service.AuthService;
import xyz.thiennam.employeems.service.MailService;
import xyz.thiennam.employeems.service.UserService;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceBean implements AuthService {

    private final UserService userService;
    private final MailService mailService;
    private final AuthenticationManager authManager;

    @Override
    @Transactional
    public ResponseEntity<SignupResponse> register(RegisterRequest request) {
        String msg = "";
        boolean isError = true;

        // check account exists
        if (userService.existsByEmail(request.getEmail())) {
            msg = "This email is already registered";
        }
        if (userService.existsByUsername(request.getUsername())) {
            msg += msg.isEmpty() ? "" : "\n";
            msg += "Username already exists";
        }
        if (msg.isEmpty()) {
            try {
                User user = new User();
                user.setUsername(request.getUsername());
                user.setEmail(request.getEmail());
                user.setPassword(request.getPassword());
                user = userService.addNewUser(user);
                msg = "Signup successful!\nPlease check your email to activate account";
                isError = false;
                mailService.sendMail(new NotificationEmail("Please Activate your Account",
                        user.getEmail(), "Thank you for signing up to EMS, " +
                        "please click on the below url to activate your account : " +
                        "http://localhost:8080/api/v1/auth/verify?q=" + user.getId()));
            } catch (Exception e) {
                msg = "Something went wrong";
            }
        }
        return new ResponseEntity<>(SignupResponse.builder()
                .isError(isError)
                .message(msg)
                .build(), isError ? HttpStatus.CONFLICT : HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> verifyAccount(String token) {
        User user = userService.findById(UUID.fromString(token));
        if (user == null) {
            return new ResponseEntity<>("Invalid Token", HttpStatus.FORBIDDEN);
        } else {
            user.setActive(true);
            userService.save(user);
            return new ResponseEntity<>("Account verified successfully", HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<AuthenticationResponse> login(LoginRequest loginRequest) {
        Authentication authentication;
        try {
            authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(
                    AuthenticationResponse.builder()
                            .errorMessage(e.getMessage())
                            .build(),
                    HttpStatus.UNAUTHORIZED);
        }

        // if login success the set context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String authToken = JwtProvider.generateAccessToken(authentication);
        String refreshToken = JwtProvider.generateRefreshToken(authentication);
        AuthenticationResponse response = AuthenticationResponse.builder()
                .authToken(authToken)
                .refreshToken(refreshToken)
                .username(loginRequest.getUsername())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AuthenticationResponse> refreshToken(RefreshTokenRequest request) {
        String errorMessage;
        if (request.getRefreshToken() == null) {
            errorMessage = "Refresh token is missing";
        } else {
            try {
                DecodedJWT decoder = JwtProvider.verify(request.getRefreshToken());
                User user = userService.findByUsername(decoder.getSubject());
                String newAuthToken = JwtProvider.generateAccessToken(user);

                AuthenticationResponse response = AuthenticationResponse.builder()
                        .authToken(newAuthToken)
                        .refreshToken(request.getRefreshToken())
                        .username(decoder.getSubject())
                        .build();
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                errorMessage = e.getMessage();
            }
        }
        return new ResponseEntity<>(AuthenticationResponse.builder()
                .errorMessage(errorMessage)
                .build(), HttpStatus.FORBIDDEN);
    }
}
