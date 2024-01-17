package com.flight.manager.flightmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import com.flight.manager.flightmanager.exception.NonexistingEntityException;
import com.flight.manager.flightmanager.service.UserService;
import static com.flight.manager.flightmanager.model.Role.*;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    JwtAthFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
//                 .authorizeHttpRequests(authorizeHttpRequests ->
//                                 authorizeHttpRequests
//                                         .requestMatchers(POST, "/api/auth/login", "/api/auth/register").permitAll()
//                                         .requestMatchers(GET, "/swagger-ui.html", "/swagger-ui/**", "/swagger-resources/**", "/v2/**").permitAll()
//                                         .requestMatchers(GET, "/api/articles").permitAll()
//                                         .requestMatchers(GET, "/api/users", "api/users/**").authenticated() //.hasRole(ADMIN.name())
//                                         .requestMatchers("/api/users", "api/users/**").hasRole(ROLE_ADMIN.name())
//                                         .requestMatchers("/**").permitAll() //hasAnyRole(ADMIN.name(), AUTHOR.name(), READER.name())
// //                                .requestMatchers(GET, "/**").hasAnyRole(ADMIN.name(), AUTHOR.name(), READER.name())
// //                                .requestMatchers(POST, "/**").hasAnyRole(ADMIN.name(), AUTHOR.name())
// //                                .requestMatchers(PUT, "/**").hasAnyRole(ADMIN.name(), AUTHOR.name())
// //                                .requestMatchers(DELETE, "/**").hasAnyRole(ADMIN.name(), AUTHOR.name())
//                 )


                .sessionManagement(sessionManagement ->
                        sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)


                ).exceptionHandling(exceptionHandling ->
                        exceptionHandling.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                );

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, UserDetailsService userDetailsService)
            throws Exception {
        var authManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
        return authManagerBuilder.build();
    }

    @Bean
    public HttpFirewall getHttpFirewall() {
        StrictHttpFirewall strictHttpFirewall = new StrictHttpFirewall();
        strictHttpFirewall.setAllowSemicolon(true);
        return strictHttpFirewall;
    }

//    @Bean
//    public WebSecurityCustomizer ignoringCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/**");
//    }

    @Bean
    UserDetailsService userDetailsService(UserService userService) {
        return (String username) -> {
            try {
                return userService.getUserByUsername(username);
            } catch (NonexistingEntityException ex) {
                throw new UsernameNotFoundException(ex.getMessage());
            }
        };
    }
}
