package com.ewd.report.service.Interfaces;

import com.ewd.report.dto.Credentials;
import com.ewd.report.entity.Category;
import com.ewd.report.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    boolean createAccount(User user);

    ResponseEntity<?> authentication(Credentials authenticationRequest);

    List<User> getAllUsers();

    void deleteItem(Long id);

    User getUserById(Long id);
}