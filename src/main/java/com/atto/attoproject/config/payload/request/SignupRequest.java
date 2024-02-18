package com.atto.attoproject.config.payload.request;

import java.util.Set;

public class SignupRequest {

  private String username;

  private String userId;

  private Set<String> role;

  private String password;

  public String getUsername() {
    return username;
  }

  public String getUserId() {
    return userId;
  }

  public Set<String> getRole() {
    return role;
  }

  public String getPassword() {
    return password;
  }
}
