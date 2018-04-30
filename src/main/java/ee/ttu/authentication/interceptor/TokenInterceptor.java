package ee.ttu.authentication.interceptor;

import ee.ttu.authentication.property.JWTProperty;
import ee.ttu.authentication.security.JWTTokenUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {

    private JWTTokenUtil jwtTokenUtil;
    private JWTProperty jwtProperty;

    public TokenInterceptor(JWTTokenUtil jwtTokenUtil, JWTProperty jwtProperty) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtProperty = jwtProperty;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String header = request.getHeader(jwtProperty.getHeader());
        if (header == null) return true;

        final String token = header.substring(jwtProperty.getTokenPrefix().length() + 1);
        if (jwtTokenUtil.canTokenBeRefreshed(token)) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            response.setHeader(jwtProperty.getHeader(), jwtProperty.getTokenPrefix() + " " + refreshedToken);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
