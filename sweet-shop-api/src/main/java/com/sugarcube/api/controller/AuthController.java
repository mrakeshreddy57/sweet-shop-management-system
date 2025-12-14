package com.sugarcube.api.controller;

import com.sugarcube.api.model.User;
import com.sugarcube.api.payload.JwtResponse;
import com.sugarcube.api.repository.UserRepository;
import com.sugarcube.api.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    // 1. LOGIN ENDPOINT (Generates the Token)
    @PostMapping("/login")
    public JwtResponse authenticateUser(@RequestBody User loginRequest) {

        // Use Spring Security to verify username/password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        // Set the authentication in the context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate the JWT String
        String jwt = jwtUtils.generateJwtToken(authentication);

        // Get User Details to send back to Frontend
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        // Return the JSON response with the Token
        return new JwtResponse(jwt, userDetails.getUsername(), roles);
    }

    // 2. REGISTER ENDPOINT (Creates a new User)
    @PostMapping("/register")
    public String registerUser(@RequestBody User signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return "Error: Username is already taken!";
        }

        // Create new user's account
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));

        // Assign default role
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");

        // If you want to create an Admin for testing, you can uncomment this logic later:
        // if (signUpRequest.getUsername().contains("admin")) { roles.add("ROLE_ADMIN"); }

        user.setRoles(roles);

        userRepository.save(user);

        return "User registered successfully!";
    }
}