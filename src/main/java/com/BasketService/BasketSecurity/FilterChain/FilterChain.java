package com.BasketService.BasketSecurity.FilterChain;
import com.BasketService.BasketSecurity.JwtFiltering.JwtFiltering;
import com.BasketService.BasketSecurity.SecurityUtilities.JwtTokenValidation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class FilterChain {


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return  authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public JwtFiltering jwtFiltering(JwtTokenValidation jwtTokenValidation){
         return new JwtFiltering(jwtTokenValidation);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,JwtFiltering jwtFiltering ) throws Exception {

            return
                    httpSecurity.csrf(AbstractHttpConfigurer::disable)
                            .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                            .authorizeHttpRequests(auth-> auth.anyRequest().authenticated())
                            .formLogin(AbstractHttpConfigurer::disable)
                            .addFilterBefore(jwtFiltering, UsernamePasswordAuthenticationFilter.class)
                            .build();
    }
}
