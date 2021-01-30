package com.ewd.report.controller;

import com.ewd.report.dto.Credentials;
import com.ewd.report.entity.Category;
import com.ewd.report.entity.FoundItem;
import com.ewd.report.entity.User;
import com.ewd.report.service.Interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @PostMapping("/user/create")
    public boolean createAccount(@Valid @RequestBody User user) {
        return userService.createAccount(user);
    }

    @PostMapping("/user/auth")
    public ResponseEntity<?> authentication(@RequestBody Credentials authenticationRequest) {
        return userService.authentication(authenticationRequest);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/user/{id}")
    public void deleteItem(@PathVariable("id") Long id){
        userService.deleteItem(id);
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable(value = "id") Long id) {
        return userService.getUserById(id);
    }


    @PutMapping("/item/{id}")
    public boolean updateUser(@Valid @RequestBody User user, @PathVariable("id") Long id){
        return userService.updateUser(user, id);
    }
}
