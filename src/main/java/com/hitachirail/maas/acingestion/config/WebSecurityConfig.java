package com.hitachirail.maas.acingestion.config;

import com.hitachirail.maas.securityframework.JWTUserDetailsService;
import com.hitachirail.maas.securityframework.JwtAuthenticationTokenFilter;
import com.hitachirail.maas.securityframework.JwtTokenUtil;
import com.hitachirail.maas.securityframework.authvalidation.AuthClaimVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder, PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider) throws Exception {
        authenticationManagerBuilder
                .authenticationProvider(preAuthenticatedAuthenticationProvider);
    }

    @Bean
    public PreAuthenticatedAuthenticationProvider preAuthAuthenticationProvider(final JWTUserDetailsService userDetailService) {
        PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider = new PreAuthenticatedAuthenticationProvider();
        preAuthenticatedAuthenticationProvider.setPreAuthenticatedUserDetailsService(userDetailService);

        return preAuthenticatedAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Bean
    public AuthClaimVerifier getAuthClaimVerifier() {
        return new AuthClaimVerifier();
    }

    @Bean
    public JWTUserDetailsService getJWTUserDetailsService() {
        return new JWTUserDetailsService();
    }

    @Bean
    public JwtTokenUtil getJwtTokenUtil() {
        return new JwtTokenUtil();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() {
        return new JwtAuthenticationTokenFilter();
    }

    /* Cors configuration */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.setAllowedMethods(Arrays.asList("POST", "PUT", "GET", "OPTIONS", "DELETE"));
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .cors().and()
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(AUTH_SWAGGER).permitAll()
                .antMatchers("/actuator/prometheus")
                .hasRole("PROMETHEUS")
                .antMatchers("/actuator/health")
                .anonymous()
                .anyRequest().authenticated();

        httpSecurity.headers().cacheControl();
    }

    private static final String[] AUTH_SWAGGER = {
        "/v2/api-docs",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**"
    };

}
