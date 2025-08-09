package com.weftecnologia.music_player_api.dto;

import java.util.Date;

import com.weftecnologia.music_player_api.enums.LibraryType;

public class ResponseLibraryDTO {

  private String id;
  private String userId;
  private String refId;
  private LibraryType type;
  private Date createdAt;

  public ResponseLibraryDTO(String id, String userId, String refId, LibraryType type, Date createdAt) {
    this.id = id;
    this.userId = userId;
    this.refId = refId;
    this.type = type;
    this.createdAt = createdAt;
  }

  public String getId() {
    return id;
  }

  public String getUserId() {
    return userId;
  }

  public String getRefId() {
    return refId;
  }

  public LibraryType getType() {
    return type;
  }

  public Date getCreatedAt() {
    return createdAt;
  }
}
