package xyz.thiennam.employeems.service;

import org.springframework.http.ResponseEntity;
import xyz.thiennam.employeems.entity.dto.*;

public interface AuthService {

    ResponseEntity<SignupResponse> register(RegisterRequest request);

    ResponseEntity<String> verifyAccount(String token);

    ResponseEntity<AuthenticationResponse> login(LoginRequest loginRequest);

    ResponseEntity<AuthenticationResponse> refreshToken(RefreshTokenRequest refreshTokenRequest);

}
