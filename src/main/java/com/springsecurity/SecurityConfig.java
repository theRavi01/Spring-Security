package com.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(Customizer.withDefaults())
	            .authorizeHttpRequests(authorize -> authorize
	                .anyRequest().authenticated()
	            ).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	            .httpBasic(Customizer.withDefaults());
//	        formLogin() use when you want default login form on browser.
//	            .formLogin(Customizer.withDefaults());
	        return http.build();
	    }
	    
//	    In Memory create Users with role(user,admin,customer).
	    @Bean
	    public UserDetailsService userDetailsService() {
	    	UserDetails user1  = User.withUsername("user")
	    			.password("{noop}userPass")
	    			.roles("USER")
	    			.build();
	    	
	    	UserDetails admin = User.withUsername("admin")
	    			.password("{noop}adminPass")
	    			.roles("ADMIN")
	    			.build();
	    	
	    	return new InMemoryUserDetailsManager(user1, admin);
	    }

}
