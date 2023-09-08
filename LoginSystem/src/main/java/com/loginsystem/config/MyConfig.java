package com.loginsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MyConfig {
	
	@Bean
	public UserDetailsService getDetailsService() {
		return new UserDetailsServicesImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(this.passwordEncoder());
		authenticationProvider.setUserDetailsService(this.getDetailsService());
		return authenticationProvider;
	}
	
	@Bean
	public SecurityFilterChain getCongiure(HttpSecurity http) throws Exception {
		
		http .csrf().disable()
			 .authorizeHttpRequests()
			 .requestMatchers("/user/**").hasRole("USER")
			 .requestMatchers("/**")
			 .permitAll()
			 .anyRequest().authenticated().and()
			 .formLogin()
			 .loginPage("/signin")
			 .defaultSuccessUrl("/user/dashboard")
			 .loginProcessingUrl("/signin-process");
		http.authenticationProvider(this.authenticationProvider());
		return http.build();
	}
	
	
	
	
	
	
	
}
