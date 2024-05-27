package com.uade.tpo.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uade.tpo.ecommerce.entity.User;
import com.uade.tpo.ecommerce.entity.dto.UserResponse;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query(value = "select id, avatar_img, firstname, lastname, email, role_id from user", nativeQuery = true)
  List<UserResponse> findAllCustom();

  @Query(value = "select * from user where email = ?1", nativeQuery = true)
  Optional<User> findByEmail(String email);

}
