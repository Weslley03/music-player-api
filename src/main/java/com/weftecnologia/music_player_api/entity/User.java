package com.weftecnologia.music_player_api.entity;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {

  @Id
  private String id;

  private String name;
  private String email;
  private String password;
  private Binary avatar;
  private boolean firtsAccess;

  public User(String id, String name, String email, String password, Binary avatar, boolean firtsAccess) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.avatar = avatar;
    this.firtsAccess = firtsAccess;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return this.name;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }

  public Binary getAvatar() {
    return this.avatar;
  }

  public boolean isFirtsAccess() {
    return firtsAccess;
  }
}