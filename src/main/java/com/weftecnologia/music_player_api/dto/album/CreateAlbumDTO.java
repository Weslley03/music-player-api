package com.weftecnologia.music_player_api.dto.album;

import jakarta.validation.constraints.NotBlank;

public class CreateAlbumDTO {

  @NotBlank(message = "o campo img é obrigatório")
  private String img;

  @NotBlank(message = "o campo title é obrigatório")
  private String title;

  @NotBlank(message = "o campo author é obrigatório")
  private String author;

  public CreateAlbumDTO(String img, String title, String password, String author) {
    this.img = img;
    this.title = title;
    this.author = author;
  };

  public String getImg() {
    return img;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }
}
