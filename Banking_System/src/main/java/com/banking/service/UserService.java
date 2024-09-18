package com.banking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.banking.config.JwtTokenProvider;
import com.banking.dto.LoginRequest;
import com.banking.dto.RegisterRequest;
import com.banking.model.User;
import com.banking.repository.UserRepository;

public class UserService implements UserDetailsService {

	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Authenticate the user and return JWT token
    public String authenticateUser(LoginRequest loginRequest) {
        // Use AuthenticationManager to authenticate credentials
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // Set the authenticated user in the SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Return the JWT token (assume it is generated elsewhere)
        return "JWT_TOKEN_PLACEHOLDER"; // Implement JWT generation
    }

    // Register a new user and save to repository
    public User registerUser(User user) {
        // Encode the user's password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user to the repository
        return userRepository.save(user);
    }

    // Fetch a user by their username
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}