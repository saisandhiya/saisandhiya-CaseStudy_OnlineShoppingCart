package com.naidu.login.Service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.naidu.login.token.JwtUtility;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component

public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	JwtUtility jwtUtility;

	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Override

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)

			throws ServletException, IOException {

		String header = request.getHeader("Authorization");

		String token = null;

		String username = null;

		if (header != null && header.startsWith("Bearer ")) {

			token = header.substring(7);

			username = jwtUtility.extractUsername(token);

		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

			if (jwtUtility.validateToken(token, userDetails)) {

				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,

						null, userDetails.getAuthorities());

				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);

	}

}
