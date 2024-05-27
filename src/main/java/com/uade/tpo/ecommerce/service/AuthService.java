package com.uade.tpo.ecommerce.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uade.tpo.ecommerce.config.JwtService;
import com.uade.tpo.ecommerce.entity.User;
import com.uade.tpo.ecommerce.entity.dto.AuthRequest;
import com.uade.tpo.ecommerce.entity.dto.AuthResponse;
import com.uade.tpo.ecommerce.entity.dto.SignupRequest;
import com.uade.tpo.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthResponse signup(SignupRequest request) {
    User user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role_id(request.getRole_id())
        .build();
    repository.save(user);

    String accessToken = jwtService.generateToken(user);
    return AuthResponse.builder()
        .accessToken(accessToken)
        .build();
  }

  public AuthResponse login(AuthRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()));
    User user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    String accessToken = jwtService.generateToken(user);
    return AuthResponse.builder()
        .accessToken(accessToken)
        .build();
  }
}
