package com.mini.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http

                // disable CSRF
                .csrf(customizer-> customizer.disable())

                // authorize authenticated requests only
                .authorizeHttpRequests(request-> request.anyRequest().authenticated())

                // for simple website login
                //.formLogin(Customizer.withDefaults())

                // for postman
                .httpBasic(Customizer.withDefaults())

                // to get new session id every refresh
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // return http.build();
                .build();
    }

    // using userDetailsService from database!!

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {

        // authentication provider
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        // password encoder - no bcrypt
        // provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());

        // password encoder - IF using bcrypt
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));

        // user details service
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    /* HOW IT WORKS - ASSIGNING USER TO BEAN ??? manually, not from database
    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user1 = User
                .withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1);
    }
    */

}
