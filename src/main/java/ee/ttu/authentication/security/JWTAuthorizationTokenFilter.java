package ee.ttu.authentication.security;

import ee.ttu.authentication.property.JWTProperty;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class JWTAuthorizationTokenFilter extends OncePerRequestFilter {

    private UserDetailsService accountService;
    private JWTTokenUtil jwtTokenUtil;
    private JWTProperty jwtProperty;

    public JWTAuthorizationTokenFilter(UserDetailsService accountService, JWTTokenUtil jwtTokenUtil, JWTProperty jwtProperty) {
        this.accountService = accountService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtProperty = jwtProperty;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("Processing authentication for '{}'", request.getRequestURL());

        final String requestHeader = request.getHeader(jwtProperty.getHeader());

        String username = null;
        String authToken = null;

        if (requestHeader != null && requestHeader.startsWith(jwtProperty.getTokenPrefix())) {
            authToken = requestHeader.substring(jwtProperty.getTokenPrefix().length() + 1);
            try {
                username = jwtTokenUtil.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
                log.error("An error occured during getting username from token", e);
            } catch (ExpiredJwtException e) {
                log.warn("The token is expired and not valid anymore");
            }
        } else {
            log.warn("Couldn't find valid token string, will ignore the header");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            log.debug("Security context was null, so authorizing user");

            UserDetails userDetails = accountService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, null);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // log.info("Authorized user '{}', setting security context", username);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
