package com.ad.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http)throws Exception {
//		http.csrf(csrf->csrf.disable())
//		.authorizeHttpRequests(requests->requests
//				.requestMatchers("/","/login","/home").permitAll()
//				.anyRequest().authenticated())
//		.formLogin(form->form.loginPage("/login"))
//		.oauth2Login(o->o.loginPage("/login"));
//		
//		return http.build();
	
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().antMatchers("/","/login","/home").permitAll()
		.anyRequest().authenticated().and().formLogin().and().oauth2Login()
		.and().csrf().disable();
	
		
	}
}
