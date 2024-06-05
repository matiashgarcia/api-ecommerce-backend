package com.uade.tpo.ecommerce.controllers.admin;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.ecommerce.entity.Role;
import com.uade.tpo.ecommerce.entity.dto.RoleRequest;
import com.uade.tpo.ecommerce.exceptions.RoleDuplicateException;
import com.uade.tpo.ecommerce.exceptions.RoleNotFoundException;
import com.uade.tpo.ecommerce.service.RoleService;

@RestController
@CrossOrigin
@RequestMapping("admin/roles")
public class AdminRoleController {

  @Autowired
  private RoleService service;

  @GetMapping
  public ResponseEntity<List<Role>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Role> getById(@PathVariable Long id) {
    Optional<Role> result = service.getById(id);
    if (result.isPresent())
      return ResponseEntity.ok(result.get());
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<Object> create(@RequestBody RoleRequest request) throws RoleDuplicateException {
    Role result = service.create(request);
    return ResponseEntity.created(URI.create("/roles/" + result.getId())).body(result);
  }

  @PutMapping
  public ResponseEntity<Object> update(@RequestBody RoleRequest request) throws RoleNotFoundException {
    Role result = service.update(request);
    return ResponseEntity.created(URI.create("/roles/" + result.getId())).body(result);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) throws RoleNotFoundException {
    service.delete(id);
    return ResponseEntity.ok().build();
  }
}
