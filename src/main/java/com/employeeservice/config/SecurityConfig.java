package com.employeeservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// Authentication : User --> Roles
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		
		auth.inMemoryAuthentication()
		.passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance())
        .withUser("user").password("password").roles("USER")
        .and()
        .withUser("admin").password("password").roles("USER", "ADMIN");

	}

	// Authorization : Role -> Access
	protected void configure(HttpSecurity http) throws Exception {
		   http.httpBasic()
		      .and().authorizeRequests()
		    .antMatchers("/employees/**").hasRole("USER")
		    .antMatchers("/departments/**").hasRole("ADMIN")
		        .and()
		    .csrf().disable().headers().frameOptions().disable();
	}
	
}