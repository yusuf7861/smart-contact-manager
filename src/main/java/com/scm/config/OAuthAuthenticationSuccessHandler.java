package com.scm.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.entities.Providers;
import com.scm.entities.User;
import com.scm.helpers.AppConstants;
import com.scm.repositories.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepo userRepo;

    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // used to test that the credentials are being fetched from the use or not
//        logger.info("OAuth Authentication Success");
        DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
//        logger.info(user.getName());
//        user.getAttributes().forEach((key, value) -> {
//            logger.info("{} : {}", key, value);
//        });
//        logger.info(user.getAuthorities().toString());

        // save user data to database
        String email = user.getAttribute("email");
        String name = user.getAttribute("name");
        String picture = user.getAttribute("picture");

        // create user and save to database
        User dbUser= new User();
        dbUser.setEmail(email);
        dbUser.setName(name);
        dbUser.setProfileLink(picture);
        dbUser.setPassword("default"); // the user will sign in directly so no password is required
        dbUser.setUserId(UUID.randomUUID().toString());
        dbUser.setProviders(Providers.GOOGLE);
        dbUser.setEnabled(true);
        dbUser.setEmailVerified(true);
        dbUser.setRoleList(List.of(AppConstants.USER_ROLE));
        dbUser.setPhoneNumber("0000000000");
        dbUser.setAbout("This User is created using OAuth2.0");

        User user1 =  userRepo.findByEmail(email).orElse(null);
        // if user already not present in the db save it otherwise redirect '/user/dashboard'
        if (user1 == null)
        {
            userRepo.save(dbUser);
            logger.info("user saved: {}", email);
        }

        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
    }
}
