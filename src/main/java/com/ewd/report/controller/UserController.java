package com.ewd.report.controller;

import com.ewd.report.dto.Credentials;
import com.ewd.report.entity.User;
import com.ewd.report.service.Interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
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


    @PostMapping("/auth")
    public ResponseEntity<?> authentication(@RequestBody Credentials authenticationRequest) {
        return userService.authentication(authenticationRequest);
    }



}
