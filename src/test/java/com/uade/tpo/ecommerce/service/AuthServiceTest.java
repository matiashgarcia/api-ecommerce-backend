package com.uade.tpo.ecommerce.service;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.uade.tpo.ecommerce.entity.User;
import com.uade.tpo.ecommerce.entity.dto.SignupRequest;
import com.uade.tpo.ecommerce.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

  @Mock
  private User user;

  @Mock
  private UserRepository repository;

  @Mock
  private PasswordEncoder passwordEncoder;

  @InjectMocks
  private AuthService service;

  @Test
  public void signupUser_cuandoSePasaUnaCategoriaInexistente_devuelveCreacionSatisfactoria() {
    // ARRANGE
    String email = "email@gmail.com";
    String firstname = "firstname";
    String lastname = "lastname";
    String password = "password";
    int roleId = 1;

    when(passwordEncoder.encode(password)).thenReturn(password);

    SignupRequest request = new SignupRequest();
    request.setEmail(email);
    request.setFirstname(firstname);
    request.setLastname(lastname);
    request.setPassword(password);
    request.setRole_id(Long.valueOf(roleId));

    User userData = new User();
    userData.setEmail(email);
    userData.setFirstname(firstname);
    userData.setLastname(lastname);
    userData.setPassword(password);
    userData.setRole_id(Long.valueOf(roleId));

    when(repository.save(userData)).thenReturn(userData);

    // ACT ASSERT
    Assertions.assertDoesNotThrow(() -> service.signup(request));
  }

}
