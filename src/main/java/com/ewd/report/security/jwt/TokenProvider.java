package com.ewd.report.security.jwt;

import com.ewd.report.entity.User;
import com.ewd.report.repository.UserRepository;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class TokenProvider implements Serializable {

	private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

	// Token is valid for 1 day
	private static final long TOKEN_VALIDITY = 999 * 24 * 60 * 60L;

	@Value("${jwt.secret}")
	private String secret;

	private  UserRepository userRepository;

	public TokenProvider(UserRepository userRepository ) {
		this.userRepository = userRepository;

	}
	public User getIdFromUsername(UserDetails userDetails) {
		return userRepository.findByUsername(userDetails.getUsername());

	}

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername(),getIdFromUsername(userDetails));
	}


	private String createToken(Map<String, Object> claims, String subject, User user) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret)
				.claim("ROLE", user.getRole())
				.claim("userId",user.getId())
				.compact();
	}

	public boolean validateToken(String authToken, UserDetails userDetails) {
		try {
			final String username = getUsernameFromToken(authToken);
			return username.equals(userDetails.getUsername()) && !isTokenExpired(authToken);
		} catch (SignatureException e) {
			log.info("Invalid JWT signature.");
			log.trace("Invalid JWT signature trace: {}",
					e);
		} catch (MalformedJwtException e) {
			log.info("Invalid JWT token.");
			log.trace("Invalid JWT token trace: {}", e);
		} catch (ExpiredJwtException e) {
			log.info("Expired JWT token.");
			log.trace("Expired JWT token trace: {}", e);
		} catch (UnsupportedJwtException e) {
			log.info("Unsupported JWT token.");
			log.trace("Unsupported JWT token trace: {}", e);
		} catch (IllegalArgumentException e) {
			log.info("JWT token compact of handler are invalid.");
			log.trace("JWT token compact of handler are invalid trace: {}", e);
		}
		return false;
	}
}