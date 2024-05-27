package com.uade.tpo.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.ecommerce.entity.Role;
import com.uade.tpo.ecommerce.entity.dto.RoleRequest;
import com.uade.tpo.ecommerce.exceptions.RoleDuplicateException;
import com.uade.tpo.ecommerce.exceptions.RoleNotFoundException;
import com.uade.tpo.ecommerce.repository.RoleRepository;

@Service
public class RoleService implements IRoleService {

  @Autowired
  private RoleRepository repository;

  public List<Role> getAll() {
    return repository.findAll();
  }

  public Optional<Role> getById(Long id) {
    return repository.findById(id);
  }

  public Role create(RoleRequest request) throws RoleDuplicateException {
    List<Role> role = repository.findByName(request.getName());
    if (role.isEmpty())
      return repository.save(new Role(request));
    throw new RoleDuplicateException();
  }

  public Role update(RoleRequest request) throws RoleNotFoundException {
    Optional<Role> role = repository.findById(request.getId());
    if (role.isPresent())
      return repository.save(new Role(request));
    throw new RoleNotFoundException();
  }

  public void delete(Long id) throws RoleNotFoundException {
    Optional<Role> role = repository.findById(id);
    if (role.isEmpty())
      throw new RoleNotFoundException();
    repository.deleteById(id);
  }

}
