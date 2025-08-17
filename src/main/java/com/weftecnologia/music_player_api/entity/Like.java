package com.weftecnologia.music_player_api.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "like")
public class Like {

  @Id
  private String id;

  private String userId;
  private String refId;
  private String refType;
  private LocalDate createdAt;

  public Like(String id, String userId, String refId, String refType, LocalDate createdAt) {
    this.id = id;
    this.userId = userId;
    this.refId = refId;
    this.refType = refType;
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

  public String getRefType() {
    return refType;
  }

  public LocalDate getCreatedAt() {
    return createdAt;
  }
}
