package com.ewd.report.service.implementations;


import com.ewd.report.dto.Credentials;
import com.ewd.report.dto.JWTToken;
import com.ewd.report.entity.Category;
import com.ewd.report.entity.User;
import com.ewd.report.exception.ResourceNotFoundException;
import com.ewd.report.repository.UserRepository;
import com.ewd.report.security.jwt.TokenProvider;
import com.ewd.report.service.Interfaces.UserService;
import net.minidev.json.JSONObject;
import org.hibernate.PropertyNotFoundException;
import org.hibernate.mapping.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

// TODO: 04.12.20  add update
@Service
public class UserServiceImpl implements UserService {

    private  UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final TokenProvider tokenProvider;

    private final JwtUserDetailsServiceImpl userDetailsService;

    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, AuthenticationManager authenticationManager1, TokenProvider tokenProvider, JwtUserDetailsServiceImpl userDetailsService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
    }


    @Override
    public boolean createAccount(User user) {

        User userExists = userRepository.findByEmail(user.getEmail());
        User usernameExists = userRepository.findByUsername(user.getUsername());

        if (userExists != null)
            throw new PropertyNotFoundException("Email Already Exist : " + user.getEmail());

        if (usernameExists != null)
            throw new PropertyNotFoundException("Username Already Exist : " + user.getUsername());

        // encrypt Password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);



        //  0 - Email
        user.setEnabled(1);
        user.setRole("ROLE_USER");
        try {
            userRepository.save(user);
            return true;
        } catch (BadCredentialsException e) {
            return false;
        }

    }



    // TODO: 30.11.20 change to email and username
    @Override
    public ResponseEntity<?> authentication(Credentials authenticationRequest) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new PropertyNotFoundException("Not found");
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());


        final String token = tokenProvider.generateToken(userDetails);

        String roles = "ROLE_USER";
        if(userDetails.getUsername().equals("moderator")){
              roles = "ROLE_MODERATOR";
        }


        JSONObject result = new JSONObject();
        result.put("token", token);
        result.put("role", roles);
        result.put("username", userDetails.getUsername());
        return  ResponseEntity.ok(result);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteItem(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        userRepository.delete(user);
        ResponseEntity.ok().build();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }


}
