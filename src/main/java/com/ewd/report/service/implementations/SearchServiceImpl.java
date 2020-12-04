package com.ewd.report.service.implementations;


import com.ewd.report.dto.Credentials;
import com.ewd.report.dto.JWTToken;
import com.ewd.report.entity.User;
import com.ewd.report.repository.UserRepository;
import com.ewd.report.security.jwt.TokenProvider;
import com.ewd.report.service.Interfaces.SearchService;
import com.ewd.report.service.Interfaces.UserService;
import org.hibernate.PropertyNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {





}
