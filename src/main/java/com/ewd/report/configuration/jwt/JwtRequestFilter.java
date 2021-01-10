package com.ewd.report.configuration.jwt;

import com.ewd.report.security.jwt.TokenProvider;
import com.ewd.report.service.implementations.JwtUserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	private final Logger log = LoggerFactory.getLogger(JwtRequestFilter.class);

	private final JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

	private final TokenProvider tokenProvider;

	public JwtRequestFilter(JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl, TokenProvider tokenProvider) {
		this.jwtUserDetailsServiceImpl = jwtUserDetailsServiceImpl;
		this.tokenProvider = tokenProvider;
	}

	// TODO: 30.11.20  change to loadUserByEmail
	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response,
									FilterChain chain)
			throws ServletException, IOException {
		String username = null;
		//log.debug("Authenticating {}", request);

		final String header = request.getHeader("Authorization");



		String jwtToken = null;


		if (header != null && header.startsWith("Bearer ")) {
			log.debug("Authenticating {}", header);
			// cut string
			jwtToken = header.substring(7);
			username = tokenProvider.getUsernameFromToken(jwtToken);
		} else {
			logger.info("JWT Token does not begin with Bearer String");
		}
		// TODO: 30.11.20  change to loadUserByEmail
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.jwtUserDetailsServiceImpl.loadUserByUsername(username);
			log.info("userDetails");
			if (tokenProvider.validateToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}

		chain.doFilter(request, response);
	}

}