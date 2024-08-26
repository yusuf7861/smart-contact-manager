package com.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.services.impl.SecurityCustomUserDetailService;

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

    @Autowired
    private OAuthAuthenticationSuccessHandler handler;


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
        httpSecurity.formLogin(formLogin ->{
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
            formLogin.successForwardUrl("/user/dashboard");
            formLogin.failureForwardUrl("/login?error=true");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");

            // manually handles the failure and redirect it to your desired page
            //TODO: Implement the onAuthenticationFailure method
//            formLogin.failureHandler(new AuthenticationFailureHandler(){
//                @Override
//                public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//                    throw new UnsupportedOperationException("Not supported yet.");
//                }
//            });

//            //TODO: Implement the onAuthenticationSuccess method
//            formLogin.successHandler(new AuthenticationSuccessHandler() {
//                @Override
//                public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                    throw new UnsupportedOperationException("Not supported yet.");
//                }
//            });
        });

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        // if you don't want to use csrf token then you can disable it by using the above line
        // if you want to make it enable then you can remove the above line
        // and create a logout form and post the request to /logout

//        // configuration for logout
        httpSecurity.logout(logout ->{
            logout.logoutUrl("/logout");
            logout.logoutSuccessUrl("/login?logout=true");
        });

//        OAuth2 login
        httpSecurity.oauth2Login(oauth -> {
                    oauth.loginPage("/login");
                    oauth.successHandler(handler);
        });

        return httpSecurity.build();
    }

    // this method will return the object of BCryptPasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}

