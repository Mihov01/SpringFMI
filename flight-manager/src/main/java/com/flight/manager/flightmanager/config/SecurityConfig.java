package com.flight.manager.flightmanager.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()  
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/register/**").permitAll()
                                .requestMatchers("/users/**").hasRole("ADMIN")
                                .requestMatchers("/updateUser/**").hasRole("ADMIN")
                                .requestMatchers("home/**").permitAll()
                                .requestMatchers("/static/**").permitAll()
                                .requestMatchers("/flights/**").permitAll()
                                .requestMatchers("/makeReservation/**").hasRole("USER")
                                .requestMatchers("/reserve/**").hasRole("USER")
                                .requestMatchers("/reservations/**").hasRole("USER")
                                .requestMatchers("/assignments/**").hasRole("CREW")
                                .requestMatchers("/deleteFlight/**").hasRole("ADMIN")
                                .requestMatchers("/createFlight/**").hasRole("ADMIN")
                                .requestMatchers("/allAssignments/**").hasRole("ADMIN")
                                .requestMatchers("/addAssignment/**").hasRole("ADMIN")
                                .requestMatchers("/saveAssignment/**").hasRole("ADMIN")
                                .requestMatchers("/deleteAssignment/**").hasRole("ADMIN")
                                .requestMatchers("/addAirline/**").hasRole("ADMIN")
                                .requestMatchers("/airline/**").hasRole("ADMIN")
                                .requestMatchers("/deleteAirline/**").hasRole("ADMIN")
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/home")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}