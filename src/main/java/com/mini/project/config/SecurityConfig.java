package com.mini.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http

                // disable CSRF
                .csrf(customizer-> customizer.disable())

                // authorize authenticated requests only
                .authorizeHttpRequests(request-> request
                        // skip authentication for register and login request
                        .requestMatchers("/api/employees/save","/api/employees/login").permitAll()
                        // request authentication for any request
                        .anyRequest().authenticated())

                // for simple website login
                //.formLogin(Customizer.withDefaults())

                // for postman
                .httpBasic(Customizer.withDefaults())

                // to get new session id every refresh
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

//                //oauth2 for Google and Github
//                .oauth2Login(Customizer.withDefaults())

                // add filter before requesting username password again - check for token first
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

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
    @Autowired
    private UserDetailsService userDetailsService;
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

    // authenticate login
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
