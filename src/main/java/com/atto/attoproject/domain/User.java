package com.atto.attoproject.domain;

import com.atto.attoproject.config.basedomain.BaseEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id")
  private String userId;

  private String password;

  @Column(name = "user_name")
  private String username;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @OneToMany(mappedBy = "user" , cascade = CascadeType.PERSIST)
  private List<AuditLog> auditLogs = new ArrayList<>();

  public User(String username, String userId, String password, Set<Role> roles) {
    this.username = username;
    this.userId = userId;
    this.password = password;
    this.roles = roles;
  }


  public Long getId() {
    return id;
  }

  public String getUserId() {
    return userId;
  }

  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return username;
  }

  public Set<Role> getRoles() {
    return roles;
  }
}
