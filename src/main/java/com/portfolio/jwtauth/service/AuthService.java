package com.portfolio.jwtauth.service;
import com.portfolio.jwtauth.dto.*;
import com.portfolio.jwtauth.model.User;
import com.portfolio.jwtauth.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service @RequiredArgsConstructor
public class AuthService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest req) {
        if (repo.existsByUsername(req.getUsername())) throw new RuntimeException("Username taken");
        if (repo.existsByEmail(req.getEmail())) throw new RuntimeException("Email already used");
        User u = new User();
        u.setUsername(req.getUsername()); u.setEmail(req.getEmail());
        u.setPassword(encoder.encode(req.getPassword()));
        repo.save(u);
        String token = jwtUtil.generateToken(u.getUsername(), u.getRole().name());
        return new AuthResponse(token, u.getUsername(), u.getRole().name());
    }

    public AuthResponse login(AuthRequest req) {
        User u = repo.findByUsername(req.getUsername())
            .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (!encoder.matches(req.getPassword(), u.getPassword())) throw new RuntimeException("Invalid credentials");
        String token = jwtUtil.generateToken(u.getUsername(), u.getRole().name());
        return new AuthResponse(token, u.getUsername(), u.getRole().name());
    }
}
