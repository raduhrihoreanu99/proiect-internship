package com.kronsoft.internship.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeHttpRequests()
			.antMatchers("/patients/**").permitAll()
			.antMatchers("/appointment/**").permitAll()
		.and().formLogin().defaultSuccessUrl("/patients")
		.and().logout().logoutSuccessUrl("/").deleteCookies("JSESSIONID")
			.clearAuthentication(true);
	}
	
	@Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		 auth.inMemoryAuthentication().withUser("user")
		 	.password(passwordEncoder().encode("password")).roles("USER");
    }
	
}
