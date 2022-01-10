package com.lauren.springboot.security;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lauren.springboot.jwt.AuthEntryPointJwt;
import com.lauren.springboot.jwt.AuthTokenFilter;
import com.lauren.springboot.services.CustomerDetailsServiceImpl;




@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		//securedEnabled= true,
		//jsr250Enable= true,
		prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	CustomerDetailsServiceImpl customerDetailsService;
	
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
//		 PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	        auth.inMemoryAuthentication()
//	          .withUser("spring")
//	          .password(encoder.encode("secret")).roles("USER");
		authenticationManagerBuilder.userDetailsService(customerDetailsService).passwordEncoder(passwordEncoder());
	}

//	@Bean
//	public DaoAuthenticationProvider authProvider() {
//	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//	    authProvider.setUserDetailsService(customerDetailsService);
//	    authProvider.setPasswordEncoder(encoder());
//	    return authProvider;
//	}
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
	
	    return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests().antMatchers("/api/auth/**").permitAll()
			.antMatchers("/api/test/**").permitAll()
			.anyRequest().authenticated();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	

}
