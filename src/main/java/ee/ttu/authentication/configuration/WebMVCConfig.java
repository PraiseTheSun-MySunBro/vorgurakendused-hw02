package ee.ttu.authentication.configuration;

import ee.ttu.authentication.interceptor.TokenInterceptor;
import ee.ttu.authentication.property.JWTProperty;
import ee.ttu.authentication.security.JWTTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebMVCConfig implements WebMvcConfigurer {

    private final JWTTokenUtil jwtTokenUtil;
    private final JWTProperty jwtProperty;

    @Autowired
    public WebMVCConfig(JWTTokenUtil jwtTokenUtil, JWTProperty jwtProperty) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtProperty = jwtProperty;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor(jwtTokenUtil, jwtProperty));
    }
}
