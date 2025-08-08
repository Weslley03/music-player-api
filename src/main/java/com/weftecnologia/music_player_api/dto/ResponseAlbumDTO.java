package com.weftecnologia.music_player_api.dto;

import java.util.Date;

public class ResponseAlbumDTO {

  private String id;
  private String img;
  private String title;
  private String author;
  private Date createdAt;

  public ResponseAlbumDTO(String id, String img, String title, String author, Date createdAt) {
    this.id = id;
    this.img = img;
    this.title = title;
    this.author = author;
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

  public Date getCreatedAt() {
    return createdAt;
  }
}
