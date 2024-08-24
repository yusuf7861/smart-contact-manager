package com.scm.config;

import com.scm.services.impl.SecurityCustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


//    This is method is used to create a user with username and password for testing purpose only
//    This is not recommended for production
//    Always use a database to store user credentials

//    @Bean
//    public UserDetailsService userDetailsService()
//    {
//        UserDetails user = User
//                .withDefaultPasswordEncoder()
//                .username("admin123")
//                .password("admin123")
//                .roles("ADMIN", "USER")
//                .build();
//
//        UserDetails user1 = User
//                .withUsername("user123")
//                .password("user123")
//                .build();
//
//        var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user, user1);
//        return inMemoryUserDetailsManager;
//    }

    @Autowired
    private SecurityCustomUserDetailService userDetailService;


    // authentication provider
    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // create object of detail service
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        // create  object  password encoder
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {

        // configuration for accessing control of the application
        // url configurations done here
        httpSecurity.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers("/user/**").authenticated()
                        .anyRequest().permitAll()
        );

        // configuration for login form, i,e; set to default
        // form related changes will be done here
        httpSecurity.formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

    // this method will return the object of BCryptPasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}

