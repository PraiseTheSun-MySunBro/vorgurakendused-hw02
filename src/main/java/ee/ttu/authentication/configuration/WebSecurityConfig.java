package ee.ttu.authentication.configuration;

import ee.ttu.authentication.property.CORSProperty;
import ee.ttu.authentication.property.JWTProperty;
import ee.ttu.authentication.security.JWTAuthenticationEntryPoint;
import ee.ttu.authentication.security.JWTAuthorizationTokenFilter;
import ee.ttu.authentication.security.JWTLoginFilter;
import ee.ttu.authentication.security.JWTTokenUtil;
import ee.ttu.authentication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JWTAuthenticationEntryPoint authenticationEntryPoint;
    private final JWTTokenUtil jwtTokenUtil;
    private final AccountService accountService;
    private final JWTProperty jwtProperty;
    private final CORSProperty corsProperty;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public WebSecurityConfig(JWTAuthenticationEntryPoint authenticationEntryPoint, JWTTokenUtil jwtTokenUtil, AccountService accountService, JWTProperty
            jwtProperty, CORSProperty corsProperty, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.jwtTokenUtil = jwtTokenUtil;
        this.accountService = accountService;
        this.jwtProperty = jwtProperty;
        this.corsProperty = corsProperty;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/auth/**").permitAll()
            .antMatchers("/js/**").permitAll()
            .antMatchers("/css/**").permitAll()
            .antMatchers("/static/**").permitAll()
            .antMatchers("/favicon.ico").permitAll()
            .anyRequest().authenticated().and()
            .addFilterBefore(new JWTLoginFilter("/auth/login", authenticationManager(), jwtTokenUtil, jwtProperty),
                    UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(new JWTAuthorizationTokenFilter(userDetailsService(), jwtTokenUtil, jwtProperty), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) {
        web
            .ignoring()
            .antMatchers(HttpMethod.GET,"/", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js");
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(corsProperty.getOrigins()));
        configuration.setAllowedMethods(Arrays.asList(corsProperty.getMethods()));
        configuration.setAllowedHeaders(Arrays.asList(corsProperty.getHeaders()));
        configuration.setExposedHeaders(Arrays.asList("Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(accountService)
            .passwordEncoder(bCryptPasswordEncoder);
    }
}