package com.example.properly.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.properly.auth.Role;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthRepository authRepository, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody Users newUser) {
        if (newUser.getRole() == null) {
            newUser.setRole(Role.TENANT);
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        authRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/me")
    public ResponseEntity<Users> currentUser(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Users user = authRepository.findByUsername(authentication.getName());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        user.setPassword(null);
        return ResponseEntity.ok(user);
    }
}
