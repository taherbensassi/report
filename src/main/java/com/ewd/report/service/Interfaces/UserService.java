package com.ewd.report.service.Interfaces;

import com.ewd.report.entity.User;

public interface UserService {


    boolean createAccount(User user);

    User findByEmail(String email);
}
