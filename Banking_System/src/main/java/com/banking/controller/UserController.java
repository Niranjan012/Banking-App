package com.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.config.JwtTokenProvider;
import com.banking.dto.JwtAuthenticationResponse;
import com.banking.dto.LoginRequest;
import com.banking.service.CustomUserDetailsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userService;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// Login endpoint
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtTokenProvider.generateToken(authentication.getName());

		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	// Register endpoint
//	@PostMapping("/register")
//	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
//
//	    // Check if user already exists
//	    if (userService.existsByUsername(registerRequest.getUsername())) {
//	        return ResponseEntity.badRequest().body("Error: Username is already taken!");
//	    }
//
//	    // Create new user
//	    User user = new User();
//	    user.setUsername(registerRequest.getUsername());
//	    user.setEmail(registerRequest.getEmail());
//	    user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
//
//	    // Save user to the database
//	    userService.saveUser(user);
//
//	    return ResponseEntity.ok("User registered successfully!");
//	}
}