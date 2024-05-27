package com.uade.tpo.ecommerce.entity;

import com.uade.tpo.ecommerce.entity.dto.RoleRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Role {

  public Role() {
  }

  public Role(RoleRequest request) {
    this.id = request.getId();
    this.name = request.getName();
    this.description = request.getDescription();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @Column
  private String description;

  // @OneToMany(mappedBy = "roles")
  // private List<User> users;

}
