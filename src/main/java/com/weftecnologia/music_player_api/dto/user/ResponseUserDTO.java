package com.weftecnologia.music_player_api.dto.user;

public class ResponseUserDTO {
  private String id;
  private String name;
  private String email;
  private String avatar;
  private boolean firtsAccess;

  public ResponseUserDTO(String id, String name, String email, String avatar, boolean firtsAccess) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.avatar = avatar;
    this.firtsAccess = firtsAccess;
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

  public String getAvatar() {
    return avatar;
  };

  public boolean isFirtsAccess() {
    return firtsAccess;
  }
};
