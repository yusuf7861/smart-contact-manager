package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    // user dashboard
    @GetMapping(value = "/dashboard")
    public String userDashboard()
    {
        return "user/dashboard";
    }

    // user profile page
    @GetMapping(value = "/profile")
    public String userProfile()
    {
        return "user/profile";
    }

    // user add contacts

    // user view contacts

    // user delete contacts

    // user search contacts

    // user update contacts

}
