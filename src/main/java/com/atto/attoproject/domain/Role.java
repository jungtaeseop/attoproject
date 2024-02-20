package com.atto.attoproject.domain;

import com.atto.attoproject.domain.enums.ERole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private ERole name;

  public Role(ERole name){
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public ERole geteName() {
    return name;
  }

}