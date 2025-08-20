package com.weftecnologia.music_player_api.dto.favorite_artist;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class AddFavoriteArtistDTO {

  @NotBlank(message = "o campo artistId é obrigatorio")
  private List<String> artistId;

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
