package ee.ttu.authentication.security;

import ee.ttu.authentication.model.Account;
import ee.ttu.authentication.property.JWTProperty;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTTokenUtil implements Serializable {

    private final JWTProperty jwtProperty;

    @Autowired
    public JWTTokenUtil(JWTProperty jwtProperty) {
        this.jwtProperty = jwtProperty;
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsTFunction.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperty.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return new Date().after(expiration);
    }

    public String generateToken(UserDetails userDetails) {
        return doGenerateToken(new HashMap<>(), userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperty.getExpirationTime()))
                .signWith(SignatureAlgorithm.HS512, jwtProperty.getSecret())
                .compact();
    }

    public boolean canTokenBeRefreshed(String token) {
        return !isTokenExpired(token);
    }

    public String refreshToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        claims.setExpiration(new Date(System.currentTimeMillis() + jwtProperty.getExpirationTime()));

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, jwtProperty.getSecret())
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        Account account = (Account) userDetails;
        final String username = getUsernameFromToken(token);

        return username.equals(account.getUsername()) && !isTokenExpired(token);
    }


}
