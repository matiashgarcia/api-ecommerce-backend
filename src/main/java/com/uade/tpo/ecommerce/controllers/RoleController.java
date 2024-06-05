package com.uade.tpo.ecommerce.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.ecommerce.entity.Role;
import com.uade.tpo.ecommerce.service.RoleService;

@RestController
@CrossOrigin
@RequestMapping("roles")
public class RoleController {

  @Autowired
  private RoleService service;

  @GetMapping
  public ResponseEntity<List<Role>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

}
