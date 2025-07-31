package com.weftecnologia.music_player_api.dto;

import org.bson.types.Binary;

public class ResponseUserDTO {
  private String id;
  private String name;
  private String email;
  private Binary avatar;

  public ResponseUserDTO(String id, String name, String email, Binary avatar) {
    this.id = id;
    this.name = name;
    this.email = name;
    this.avatar = avatar;
  };

  public String getId() {
    return id;
  };

  public String getName() {
    return name;
  };

  public String getEmail() {
    return email;
  };

  public Binary getAvatar() {
    return avatar;
  };
};
