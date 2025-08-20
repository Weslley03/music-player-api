package com.weftecnologia.music_player_api.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.weftecnologia.music_player_api.enums.LibraryType;

@Document(collection = "library")
public class Library {

  @Id
  private String id;

  private String userId;
  private String refId;
  private LibraryType type;
  private LocalDate createdAt;

  public Library(String id, String userId, String refId, LibraryType type, LocalDate createdAt) {
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

  public LocalDate getCreatedAt() {
    return createdAt;
  }
}
