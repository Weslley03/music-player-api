package com.weftecnologia.music_player_api.dto.artist;

import jakarta.validation.constraints.NotBlank;

public class CreateArtistDTO {

  @NotBlank(message = "o campo img é obrigatório")
  private String img;

  @NotBlank(message = "o campo name é obrigatório")
  private String name;

  public CreateArtistDTO(String img, String name) {
    this.img = img;
    this.name = name;
  }

  public String getImg() {
    return img;
  }

  public String getName() {
    return name;
  }
}
