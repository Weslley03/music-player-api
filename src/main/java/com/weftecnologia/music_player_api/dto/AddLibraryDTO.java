package com.weftecnologia.music_player_api.dto;

import com.weftecnologia.music_player_api.enums.LibraryType;

public class AddLibraryDTO {
  private String userId;
  private String img;
  private String title;
  private String description;
  private LibraryType type;

  public AddLibraryDTO(String userId, String img, String title, String description, LibraryType type) {
    this.userId = userId;
    this.img = img;
    this.title = title;
    this.description = description;
    this.type = type;
  }

  public String getUserId() {
    return userId;
  }

  public String getImg() {
    return img;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public LibraryType getType() {
    return type;
  }
}
