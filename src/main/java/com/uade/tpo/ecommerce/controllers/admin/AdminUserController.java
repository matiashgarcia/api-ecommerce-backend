package com.uade.tpo.ecommerce.controllers.admin;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.ecommerce.entity.User;
import com.uade.tpo.ecommerce.entity.dto.UserRequest;
import com.uade.tpo.ecommerce.exceptions.UserNotFoundException;
import com.uade.tpo.ecommerce.service.UserService;

@RestController
@RequestMapping("admin/users")
public class AdminUserController {

  @Autowired
  private UserService service;

  @GetMapping
  public ResponseEntity<List<User>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getById(@PathVariable Long id) {
    Optional<User> result = service.getById(id);
    if (result.isPresent())
      return ResponseEntity.ok(result.get());
    return ResponseEntity.noContent().build();
  }

  @PutMapping
  public ResponseEntity<Object> update(@RequestBody UserRequest request) throws UserNotFoundException {
    User result = service.update(request);
    return ResponseEntity.created(URI.create("/users/" + result.getId())).body(result);
  }

}
