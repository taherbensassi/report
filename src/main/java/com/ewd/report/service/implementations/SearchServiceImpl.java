package com.ewd.report.service.implementations;


import com.ewd.report.controller.SearchController;
import com.ewd.report.dto.Credentials;
import com.ewd.report.dto.JWTToken;
import com.ewd.report.entity.FoundItem;
import com.ewd.report.entity.User;
import com.ewd.report.repository.UserRepository;
import com.ewd.report.security.jwt.TokenProvider;
import com.ewd.report.service.Interfaces.SearchService;
import com.ewd.report.service.Interfaces.UserService;
import org.hibernate.PropertyNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {

    private final Logger log = LoggerFactory.getLogger(SearchServiceImpl.class);

    @Override
    public Map<String, String> search(FoundItem foundItem) {
        log.debug("Authenticating {}", foundItem.getAddress());

        return null;
    }
}
