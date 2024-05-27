package com.uade.tpo.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.ecommerce.entity.User;
import com.uade.tpo.ecommerce.entity.dto.UserRequest;
import com.uade.tpo.ecommerce.exceptions.UserDuplicateException;
import com.uade.tpo.ecommerce.exceptions.UserNotFoundException;

public interface IUserService {
  public List<User> getAll();

  public Optional<User> getById(Long id);

  public User create(UserRequest request) throws UserDuplicateException;

  public User update(UserRequest request) throws UserNotFoundException;

}
