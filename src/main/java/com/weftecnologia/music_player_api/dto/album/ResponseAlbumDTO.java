package com.weftecnologia.music_player_api.dto.album;

import java.util.Date;

public class ResponseAlbumDTO {

  private String id;
  private String img;
  private String title;
  private String author;
  private Date createdAt;
  private boolean liked;

  public ResponseAlbumDTO(String id, String img, String title, String author, Date createdAt, boolean liked) {
    this.id = id;
    this.img = img;
    this.title = title;
    this.author = author;
    this.createdAt = createdAt;
    this.liked = liked;
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

  public boolean isLiked() {
    return liked;
  }
}
