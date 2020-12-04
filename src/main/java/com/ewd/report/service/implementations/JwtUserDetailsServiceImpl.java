package com.ewd.report.service.implementations;

import java.util.ArrayList;

import com.ewd.report.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.el.PropertyNotFoundException;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	private final Logger log = LoggerFactory.getLogger(JwtUserDetailsServiceImpl.class);

	public JwtUserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.debug("Authenticating {}", username);

		com.ewd.report.entity.User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new PropertyNotFoundException("Username not found");
		}
		log.debug("User Exist {}", username);

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	// TODO: 04.12.20   should be in top
	public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
		com.ewd.report.entity.User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new PropertyNotFoundException("Email not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				new ArrayList<>());
	}


}