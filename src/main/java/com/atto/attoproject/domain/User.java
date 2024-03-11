package com.atto.attoproject.domain;

import com.atto.attoproject.config.basedomain.BaseEntity;
import com.atto.attoproject.domain.enums.ERole;
import com.atto.attoproject.service.UserDetailsImpl;
import jakarta.persistence.*;

import java.util.*;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

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

  private Integer failedLoginAttempts;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @OneToMany(mappedBy = "user" , cascade = CascadeType.PERSIST)
  private List<AuditLog> auditLogs = new ArrayList<>();

  public User(String userId, String password, String username, Integer failedLoginAttempts, Set<Role> roles) {
    this.userId = userId;
    this.password = password;
    this.username = username;
    this.failedLoginAttempts = failedLoginAttempts;
    this.roles = roles;
  }

  private User(Long id, String userId, String password, String username, Integer failedLoginAttempts, Set<Role> roles) {
    this.id = id;
    this.userId = userId;
    this.password = password;
    this.username = username;
    this.failedLoginAttempts = failedLoginAttempts;
    this.roles = roles;
  }

  public static User from(UserDetailsImpl userDetailsImpl){
    return new User(
             userDetailsImpl.getId()
            , userDetailsImpl.getUserId()
            , userDetailsImpl.getPassword()
            , userDetailsImpl.getUsername()
            , userDetailsImpl.getFailedLoginAttempts()
            , toRoleSet(userDetailsImpl.getAuthorities())
    );
  }

  private static Set<Role> toRoleSet(Collection<? extends GrantedAuthority> authorities) {
    return authorities.stream()
            .map(authority -> {
              switch (authority.getAuthority()) {
                case "ROLE_ADMIN":
                  return new Role(ERole.ROLE_ADMIN);
                case "ROLE_USER":
                  return new Role(ERole.ROLE_USER);
              }
                return null;
            })
            .collect(Collectors.toSet());
  }

  public Integer getFailedLoginAttempts() {
    return failedLoginAttempts;
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
