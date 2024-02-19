package com.atto.attoproject.config.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class SignupRequest {
  @NotBlank(message = "사용자 이름")
  private String username;

  @NotBlank(message = "사용자 ID")
  private String userId;

  private Set<String> role;

  @NotBlank(message = "사용자 패스워드")
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
