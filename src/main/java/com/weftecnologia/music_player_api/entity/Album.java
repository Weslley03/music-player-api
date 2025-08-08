package com.weftecnologia.music_player_api.entity;

import java.util.Date;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "album")
public class Album {

  @Id
  private String id;

  private Binary img;
  private String title;
  private String author;
  private Date createdAt;

  public Album(String id, Binary img, String title, String author, Date createdAt) {
    this.id = id;
    this.img = img;
    this.title = title;
    this.author = author;
    this.createdAt = createdAt;
  }

  public String getId() {
    return id;
  }

  public Binary getImg() {
    return img;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public Date getCreatedAt() {
    return createdAt;
  }
}
