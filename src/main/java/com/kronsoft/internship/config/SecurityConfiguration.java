package com.kronsoft.internship.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kronsoft.internship.config.authentication.CustomAuthneticationFailureHandler;
import com.kronsoft.internship.config.authentication.CustomUserDetailsService;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
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
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeHttpRequests()
		    .antMatchers("/account/register").permitAll()
			.antMatchers("/patients/**", "/appointment/**", "/account/**").authenticated()
		.and().formLogin()
			.defaultSuccessUrl("/swagger-ui/index.html")
			.failureHandler(new CustomAuthneticationFailureHandler())
		.and().logout()
			.logoutSuccessUrl("/")
			.deleteCookies("JSESSIONID")
			.clearAuthentication(true);
	}
	
}
