package com.naidu.login.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.naidu.login.Entity.Login;
import com.naidu.login.Service.CustomUserDetails;
import com.naidu.login.Service.ICustomerService;
import com.naidu.login.request.LoginRequest;
import com.naidu.login.response.JSONResponse;
import com.naidu.login.token.JwtUtility;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("http://localhost:4200")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtility jwtUtil;

	@Autowired
	ICustomerService userService;

//	@PostMapping("/signIn")
//	public String signIn(@RequestBody LoginRequest jwtRequest) {
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
//		if (authentication.isAuthenticated()) {
//			return jwtUtil.generateToken(jwtRequest.getUsername());
//			//JSONResponse js=new JSONResponse(, null, null)
//		} else {
//			throw new UsernameNotFoundException("Invalid credentials!");
//		}
//
//	}
	
	@PostMapping("/signIn")
	public ResponseEntity<JSONResponse> signIn(@RequestBody LoginRequest jwtRequest) {
	    Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
	    
	    if (authentication.isAuthenticated()) {
	        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
	        String jwtToken = jwtUtil.generateToken(jwtRequest.getUsername());
	        List<String> roles = userDetails.getAuthorities().stream()
	                .map(GrantedAuthority::getAuthority)
	                .collect(Collectors.toList());

	        JSONResponse jsonResponse = new JSONResponse(jwtToken, jwtRequest.getUsername(), roles);
	        return ResponseEntity.ok(jsonResponse);
	    } else {
	        throw new UsernameNotFoundException("Invalid credentials!");
	    }
	}


	// Endpoint to register new user
	@PostMapping("/register")
	public ResponseEntity<Login> register(@Valid @RequestBody Login user) {
		return ResponseEntity.ok(userService.register(user));
	}

	@PostMapping("/signOut/{username}")
	public ResponseEntity<String> signOut(@PathVariable String username) {
		// Check if the username matches the authenticated user's username
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

		if (userDetails.getUsername().equals(username)) {
			// Log the username of the user attempting to sign out
			System.out.println("User '" + username + "' signed out.");
			SecurityContextHolder.clearContext();
			return ResponseEntity.ok("Successfully signed out");
		} else {
			throw new UsernameNotFoundException("User not found: " + username);
		}
	}

}
