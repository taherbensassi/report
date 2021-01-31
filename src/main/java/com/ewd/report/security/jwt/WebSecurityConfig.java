package com.ewd.report.security.jwt;

import com.ewd.report.configuration.jwt.JwtAuthenticationEntryPoint;
import com.ewd.report.configuration.jwt.JwtRequestFilter;
import com.ewd.report.configuration.jwt.SimpleCORSFilter;
import com.ewd.report.service.implementations.JwtUserDetailsServiceImpl;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	private final JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;

	private final JwtRequestFilter jwtRequestFilter;

	private final SimpleCORSFilter corsFilter;

	private static final String[] AUTH_WHITELIST = {
			"/swagger-resources/**",
			"/swagger-ui.html",
			"/v2/api-docs",
			"/webjars/**"
	};

	public WebSecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl, JwtRequestFilter jwtRequestFilter, SimpleCORSFilter corsFilter) {
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.jwtUserDetailsServiceImpl = jwtUserDetailsServiceImpl;
		this.jwtRequestFilter = jwtRequestFilter;
		this.corsFilter = corsFilter;
	}


	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(AUTH_WHITELIST);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class).exceptionHandling()
				.and().csrf().disable()
				.headers().frameOptions().disable().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().authorizeRequests()
				.antMatchers(HttpMethod.POST, "/api/user/create").permitAll()
				.antMatchers(HttpMethod.POST, "/api/user/auth").permitAll()
				.anyRequest().authenticated().and().
						exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

}