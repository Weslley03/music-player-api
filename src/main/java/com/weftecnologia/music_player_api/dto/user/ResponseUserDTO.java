package com.weftecnologia.music_player_api.dto.user;

public class ResponseUserDTO {
  private String id;
  private String name;
  private String email;
  private String avatar;

  public ResponseUserDTO(String id, String name, String email, String avatar) {
    this.id = id;
    this.name = name;
    this.email = email;
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

  public String getAvatar() {
    return avatar;
  };
};
