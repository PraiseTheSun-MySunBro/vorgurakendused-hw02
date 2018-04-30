package ee.ttu.authentication.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.ttu.authentication.model.Account;
import ee.ttu.authentication.property.JWTProperty;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    private JWTProperty jwtProperty;
    private JWTTokenUtil jwtTokenUtil;

    public JWTLoginFilter(String url, AuthenticationManager authManager, JWTTokenUtil jwtTokenUtil, JWTProperty jwtProperty) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);

        this.jwtProperty = jwtProperty;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException, IOException {
        Account account = new ObjectMapper()
                .readValue(req.getInputStream(), Account.class);
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        account.getUsername(),
                        account.getPassword(),
                        Collections.emptyList()
                )
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException {
        final String token = jwtTokenUtil.generateToken(new Account(null, auth.getName(), null, null,
                null, null, null));
        res.addHeader(jwtProperty.getHeader(), jwtProperty.getTokenPrefix() + " " + token);
        res.getWriter().write("{\"token\":\"" + token + "\"}");
    }
}

