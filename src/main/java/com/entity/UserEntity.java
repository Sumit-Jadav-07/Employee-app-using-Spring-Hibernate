package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "users")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer userId;
  @Column(length = 50, nullable = false)
  String name;
  @Column(unique = true)
  String email;
  String password;
}
