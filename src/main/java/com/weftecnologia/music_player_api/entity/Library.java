package com.weftecnologia.music_player_api.entity;

import java.util.Date;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.weftecnologia.music_player_api.enums.LibraryType;

@Document(collection = "library")
public class Library {

  @Id
  private String id;

  private String userId;
  private Binary img;
  private String title;
  private String description;
  private Date createdAt;
  private LibraryType type;

  public Library(String id, String userId, Binary img, String title, String description, Date createdAt,
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

  public Binary getImg() {
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
