package com.weftecnologia.music_player_api.entity;

import java.util.Date;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "artist")
public class Artist {

  @Id
  private String id;

  private Binary img;
  private String name;
  private Date createdAt;

  public Artist(String id, Binary img, String name, Date createdAt) {
    this.id = id;
    this.img = img;
    this.name = name;
    this.createdAt = createdAt;
  }

  public String getId() {
    return id;
  }

  public Binary getImg() {
    return img;
  }

  public String getName() {
    return name;
  }

  public Date getCreatedAt() {
    return createdAt;
  }
}
