package com.uade.tpo.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.ecommerce.entity.Role;
import com.uade.tpo.ecommerce.entity.dto.RoleRequest;
import com.uade.tpo.ecommerce.exceptions.RoleDuplicateException;
import com.uade.tpo.ecommerce.exceptions.RoleNotFoundException;

public interface IRoleService {
  public List<Role> getAll();

  public Optional<Role> getById(Long id);

  public Role create(RoleRequest request) throws RoleDuplicateException;

  public Role update(RoleRequest request) throws RoleNotFoundException;

  public void delete(Long id) throws RoleNotFoundException;

}
