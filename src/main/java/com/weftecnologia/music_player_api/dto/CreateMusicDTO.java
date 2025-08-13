package com.weftecnologia.music_player_api.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateMusicDTO {

  @NotBlank(message = "albumId é obrigatório")
  private String albumId;

  @NotBlank(message = "artistId é obrigatório")
  private String artistId;

  @NotBlank(message = "img é obrigatório")
  private String img;

  @NotBlank(message = "title é obrigatório")
  private String title;

  @NotBlank(message = "author é obrigatório")
  private String author;

  @NotBlank(message = "src é obrigatório")
  private String src;

  public CreateMusicDTO(String img, String title, String author, String src) {
    this.img = img;
    this.title = title;
    this.author = author;
    this.src = src;
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
}
