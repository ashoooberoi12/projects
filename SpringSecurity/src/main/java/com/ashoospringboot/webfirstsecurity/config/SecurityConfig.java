package com.ashoospringboot.webfirstsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChanin(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.csrf(customizer -> customizer.disable());
		httpSecurity.authenticationManager(request -> request.anyRequest().authenticated());
		
		return httpSecurity.build();
		
	}

}
