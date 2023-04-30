package com.marella.store.controller;

import com.marella.store.model.User;
import com.marella.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class UserController {

    @Autowired
    UserService userService;



    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "/login/add-user";
    }

    @GetMapping("/")
    public String showUserList(Model model) {
        model.addAttribute("users", userService.getAll());
        return "/login/index";
    }


    @PostMapping("/adduser")
    public String addUser(@Validated User user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "/login/add-user";
        }
        userService.addUser(user);
        return "redirect:/login/";

    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "/login/update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Validated User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "/login/update-user";
        }

        userService.addUser(user);
        return "redirect:/login/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/login/";
    }

}
