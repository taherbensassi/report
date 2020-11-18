package com.ewd.report.service.implementations;


import com.ewd.report.entity.User;
import com.ewd.report.exception.ResourceNotFoundException;
import com.ewd.report.repository.FoundItemRepository;
import com.ewd.report.repository.UserRepository;
import com.ewd.report.service.Interfaces.UserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private  UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public boolean createAccount(User user) {

        User userExists = findByEmail(user.getEmail());

        if (userExists != null)
            // TODO: 17.11.20 change Exception message create EntityException
            throw new ResourceNotFoundException("E-mail exist","","");

        // encrypt Password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(1);
        user.setRole("ROLE_USER");

        try {
            userRepository.save(user);
            return true;
        } catch (BadCredentialsException e) {
            return false;
        }

    }


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
