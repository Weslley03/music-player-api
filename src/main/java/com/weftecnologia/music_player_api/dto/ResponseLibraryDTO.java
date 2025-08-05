package com.weftecnologia.music_player_api.dto;

import java.util.Date;

import com.weftecnologia.music_player_api.enums.LibraryType;

public class ResponseLibraryDTO {

  private String id;
  private String userId;
  private String img;
  private String title;
  private String description;
  private Date createdAt;
  private LibraryType type;

  public ResponseLibraryDTO(String id, String userId, String img, String title, String description, Date createdAt,
      LibraryType type) {
    this.id = id;
    this.userId = userId;
    this.img = img;
    this.title = title;
    this.description = description;
    this.createdAt = createdAt;
    this.type = type;
  }

  public String getId() {
    return id;
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

  public Date getCreatedAt() {
    return createdAt;
  }

  public LibraryType getType() {
    return type;
  }
}
