package xyz.thiennam.employeems.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationResponse {
    private String errorMessage;
    private String authToken;
    private String refreshToken;
    private String username;
}
