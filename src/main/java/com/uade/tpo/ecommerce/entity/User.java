package com.uade.tpo.ecommerce.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.uade.tpo.ecommerce.entity.dto.UserRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails {

  public User(UserRequest request) {
    this.id = request.getId();
    this.avatar_img = request.getAvatar_img();
    this.firstname = request.getFirstname();
    this.lastname = request.getLastname();
    this.email = request.getEmail();
    this.password = request.getPassword();
    this.role_id = request.getRole_id();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String avatar_img;

  @Column
  private String firstname;

  @Column
  private String lastname;

  @Column
  private String email;

  @Column
  private String password;

  // https://stackoverflow.com/questions/27930449/jpa-many-to-one-relation-need-to-save-only-id
  @ManyToOne(targetEntity = Role.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "role_id", insertable = false, updatable = false)
  private Role role;

  @Column(name = "role_id")
  private Long role_id;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.getName()));
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
