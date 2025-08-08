package com.weftecnologia.music_player_api.dto;

import java.util.Date;

public class ResponseArtistDTO {
  private String id;
  private String img;
  private String name;
  private Date createdAt;

  public ResponseArtistDTO(String id, String img, String name, Date createdAt) {
    this.id = id;
    this.img = img;
    this.name = name;
    this.createdAt = createdAt;
  };

  public String getId() {
    return id;
  }

  public String getImg() {
    return img;
  }

  public String getName() {
    return name;
  }

  public Date getCreatedAt() {
    return createdAt;
  }
}
