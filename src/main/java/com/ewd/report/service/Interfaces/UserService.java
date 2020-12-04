package com.ewd.report.service.Interfaces;

import com.ewd.report.dto.Credentials;
import com.ewd.report.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    boolean createAccount(User user);

    User findByEmail(String email);

    User findByUsername(String username);

    ResponseEntity<?> authentication(Credentials authenticationRequest);


}