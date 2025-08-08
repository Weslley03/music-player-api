package com.weftecnologia.music_player_api.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "music")
public class Music {

  @Id
  private String id;

  private String img;
  private String title;
  private String author;
  private String src;
  private Date createdAt;

  public Music(String id, String img, String title, String author, String src, Date createdAt) {
    this.id = id;
    this.img = img;
    this.title = title;
    this.author = author;
    this.src = src;
    this.createdAt = createdAt;
  }

  public String getId() {
    return id;
  }

  public String getImg() {
    return img;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public String getSrc() {
    return src;
  }

  public Date getCreatedAt() {
    return createdAt;
  }
}
