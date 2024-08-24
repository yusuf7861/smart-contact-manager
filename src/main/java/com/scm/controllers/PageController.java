package com.scm.controllers;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String home(Model model)
    {
        model.addAttribute("name", "Substring technologies");
        model.addAttribute("username", "Yusuf Jamal");
        model.addAttribute("github", "http://www.github.com/yusuf7861");
        return "home";
    }

    //    about router
    @RequestMapping("/about")
    public String aboutPage() {
        return "about";
    }

    //    services route
    @RequestMapping("/services")
    public String servicesPage()
    {
        return "services";
    }

    @RequestMapping("/contact")
    public String contact()
    {
        return "contact";
    }

    @RequestMapping("/login")
    public String login()
    {
        return new String("login");
    }

    @RequestMapping("/register")
    public String register(Model model)
    {
        // creating an empty object of class UserForm
        UserForm userForm = new UserForm();

        // you can also send default data to any field on register page, by default that data will shown to the user
//        userForm.setName("Yusuf Jamal");
        model.addAttribute("userForm", userForm);
        return "register";
    }


    // Processing registers
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult)
    {
        System.out.println("processing registration");
//        fetching form data
//        validate form data
        if(bindingResult.hasErrors())
        {
            System.out.println("Error in form data");
            return "register";
        }
        //  save to database

//        userForm -> User
        User user= User.builder()
                .name(userForm.getName())
                .email(userForm.getEmail())
                .password(userForm.getPassword())
                .about(userForm.getAbout())
                .phoneNumber(userForm.getPhoneNumber())
                .profileLink("" +
                        "https://img.freepik.com/free-vector/blue-circle-with-white-user_78370-4707.jpg?size=626&ext=jpg&ga=GA1.1.981727233.1723551308&semt=ais_hybrid")
                .build();

        User savedUser =  userService.saveUser(user);

//        redirect to login page
        return "redirect:/register";
    }
}
