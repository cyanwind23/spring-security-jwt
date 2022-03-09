package xyz.thiennam.employeems.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import xyz.thiennam.employeems.entity.Role;
import xyz.thiennam.employeems.entity.User;

import java.util.Date;
import java.util.stream.Collectors;

public class JwtProvider {

    public static final Long EXP_ACCESS_TOKEN = 15 * 60 * 1000L; // 15 minutes
    public static final Long EXP_REFRESH_TOKEN = 60 * 60 * 1000L; // 1 hour
    public static final String TOKEN_ISSUER = "http://localhost:8080";
    // TODO: need to change this
    private static final String PRIVATE_KEY = "HopeYouHappy23";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(PRIVATE_KEY.getBytes());

    public static String generateAccessToken(Authentication user) {
        return JWT.create()
                .withSubject(user.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXP_ACCESS_TOKEN))
                .withClaim("roles", user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withIssuer(TOKEN_ISSUER)
                .sign(ALGORITHM);
    }

    public static String generateAccessToken(User user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXP_ACCESS_TOKEN))
                .withClaim("roles", user.getRoles().stream()
                        .map(Role::getRoleName)
                        .collect(Collectors.toList()))
                .withIssuer(TOKEN_ISSUER)
                .sign(ALGORITHM);
    }

    public static String generateRefreshToken(Authentication user) {
        return JWT.create()
                .withSubject(user.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXP_REFRESH_TOKEN))
                .withIssuer(TOKEN_ISSUER)
                .sign(ALGORITHM);
    }

    public static DecodedJWT verify(String token) {
        JWTVerifier verifier = JWT.require(ALGORITHM).build();
        return verifier.verify(token);
    }

}
