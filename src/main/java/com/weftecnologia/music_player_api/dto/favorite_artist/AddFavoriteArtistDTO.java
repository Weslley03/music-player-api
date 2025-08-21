package com.weftecnologia.music_player_api.dto.favorite_artist;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class AddFavoriteArtistDTO {

  @NotEmpty(message = "artistId não pode estar vazio")
  private List<@NotBlank(message = "o campo artistId é obrigatorio") String> artistId;

  @NotBlank(message = "o campo userId é obrigatorio")
  private String userId;

  public AddFavoriteArtistDTO(List<String> artistId, String userId) {
    this.artistId = artistId;
    this.userId = userId;
  }

  public List<String> getArtistId() {
    return artistId;
  }

  public String getUserId() {
    return userId;
  }
}
