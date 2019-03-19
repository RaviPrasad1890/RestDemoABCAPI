package com.abc.bank.tokenservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance()).withUser("user").password("user")
				.roles("USER").and().withUser("admin").password("admin")
				.roles("USER", "ADMIN");
	}


	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic().and()
		.authorizeRequests().antMatchers("/customer/token/counter/{counterId}")
		.hasRole("ADMIN")
		.antMatchers("/customer/**").hasAnyRole("ADMIN","USER")
		.and().csrf().disable()
		.headers().frameOptions().disable();
	}

}