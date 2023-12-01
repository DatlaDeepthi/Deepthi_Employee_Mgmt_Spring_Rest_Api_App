package com.glca.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.glca.demo.service.UserDetailsServiceImpl;

@SuppressWarnings("deprecation")
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/emp/user", "/emp/role").permitAll()
				.antMatchers(HttpMethod.POST, "/emp/addemployee").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.PUT, "/emp/updateemployee").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/emp/deleteemployee/*").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.GET, "/emp/employeesById/*").hasAnyAuthority("ADMIN", "USER")
				.antMatchers(HttpMethod.GET, "/emp/getAllEmployees").hasAnyAuthority("ADMIN", "USER")
				.antMatchers(HttpMethod.GET, "/emp/employees/search/*").hasAnyAuthority("ADMIN", "USER")
				.antMatchers(HttpMethod.GET, "/emp/employee-asc/sort/").hasAnyAuthority("ADMIN", "USER")
				.antMatchers(HttpMethod.GET, "/emp/employee-desc/sort/").hasAnyAuthority("ADMIN", "USER").anyRequest()
				.authenticated().and().httpBasic().and().cors().and().csrf().disable();
	}

}
