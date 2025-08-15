package com.weftecnologia.music_player_api.dto.music;

import java.util.Date;

public class ResponseMusicDTO {

  private String id;
  private String albumId;
  private String artistId;
  private String img;
  private String title;
  private String author;
  private String src;
  private Date createdAt;

  public ResponseMusicDTO(
      String id,
      String albumId,
      String artistId,
      String img,
      String title,
      String author,
      String src,
      Date createdAt) {
    this.id = id;
    this.albumId = albumId;
    this.artistId = artistId;
    this.img = img;
    this.title = title;
    this.author = author;
    this.src = src;
    this.createdAt = createdAt;
  }

  public String getId() {
    return id;
  }

  public String getAlbumId() {
    return albumId;
  }

  public String getArtistId() {
    return artistId;
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
