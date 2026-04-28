package com.customerinfo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	//PasswordEncoder - BCrypt Password Encoder

	@Bean
	static PasswordEncoder passEncode() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) {
		
		http.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests((authorize)->{
			authorize.requestMatchers(HttpMethod.POST,"/customerapi/**").hasRole("ADMIN");
			authorize.requestMatchers(HttpMethod.PUT,"/customerapi/**").hasAnyRole("MANAGER","ADMIN");
			authorize.requestMatchers(HttpMethod.DELETE,"/customerapi/**").hasRole("ADMIN");
			authorize.anyRequest().authenticated();
			}).httpBasic(Customizer.withDefaults());
		return http.build();
		
	}
	
	@Bean
	UserDetailsService userDetailsService() {
		UserDetails admin = User.builder()
		.username("Admin")
		.password(passEncode().encode("apex@123"))
		.roles("ADMIN")
		.build();
	
	
		UserDetails manager = User.builder()
		.username("Manager")
		.password(passEncode().encode("manager@123"))
		.roles("MANAGER")
		.build();
	
	
		UserDetails user1 = User.builder()
		.username("Amanda")
		.password(passEncode().encode("test@123"))
		.roles("USER")
		.build();
	
		UserDetails user2 = User.builder()
		.username("Nora")
		.password(passEncode().encode("main@123"))
		.roles("USER")
		.build();


	return new InMemoryUserDetailsManager(admin,manager,user1,user2);
	}
	
}
