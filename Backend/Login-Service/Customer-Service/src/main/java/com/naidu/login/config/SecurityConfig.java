package com.naidu.login.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.naidu.login.Service.CustomUserDetailsService;
import com.naidu.login.Service.JwtAuthFilter;
import com.naidu.login.token.EntryPointJwt;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

	// Configuring http security and opening the endpoints to the public
	@Autowired
	EntryPointJwt entryPointJwt;
	@Autowired
	JwtAuthFilter filter;

	@Bean
	UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.cors()
                .configurationSource(
                        new CorsConfigurationSource() {
                            @Override
                            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                                CorsConfiguration config = new CorsConfiguration();
                                config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                                config.setAllowedMethods(Collections.singletonList("*"));
                                config.setAllowCredentials(true);
                                config.setAllowedHeaders(Collections.singletonList("*"));
                                config.setExposedHeaders(Arrays.asList("Authorization"));       // because an authorization header will be sent from UI to backends
                                config.setMaxAge(3600L);
                                return config;
                            }
                        }
                )
				.and().csrf().disable()
				.exceptionHandling().authenticationEntryPoint(entryPointJwt).and()
				.authorizeHttpRequests()
				.requestMatchers("/register", "/signIn","/signOut/**").permitAll()
				.requestMatchers("/customer/access/**","/admin/**","/agent/**").authenticated()
				.and()

				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//no session will be created

				.and()
				// .authenticationProvider(authenticationProvider())

				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean

	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;

	}

	// Creating bean of authentication manager to be used while getting the token
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	// Creating a bean of password encoder
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
