package com.ewd.report.controller;

import com.ewd.report.entity.User;
import com.ewd.report.service.Interfaces.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public boolean createAccount(@Valid @RequestBody User user) {
        return userService.createAccount(user);
    }



}
