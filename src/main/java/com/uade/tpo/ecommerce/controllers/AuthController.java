package com.uade.tpo.ecommerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.ecommerce.entity.User;
import com.uade.tpo.ecommerce.entity.dto.AuthRequest;
import com.uade.tpo.ecommerce.entity.dto.AuthResponse;
import com.uade.tpo.ecommerce.entity.dto.SignupRequest;
import com.uade.tpo.ecommerce.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService service;

  @PostMapping("/signup")
  public ResponseEntity<User> signup(@RequestBody SignupRequest request) {
    try {
      service.signup(request);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
    return ResponseEntity.ok(service.login(request));
  }

}