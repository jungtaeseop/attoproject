package com.atto.attoproject.config.payload.response;

import java.util.List;

public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String username;
  private String userId;
  private List<String> roles;

  public JwtResponse(String accessToken, Long id, String userId, String username, List<String> roles) {
    this.token = accessToken;
    this.id = id;
    this.username = username;
    this.userId = userId;
    this.roles = roles;
  }

  public String getUserId() {
    return userId;
  }


  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getRoles() {
    return roles;
  }
}
