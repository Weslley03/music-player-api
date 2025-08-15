package com.weftecnologia.music_player_api.dto.favorite_artist;

import jakarta.validation.constraints.NotBlank;

public class AddFavoriteArtistDTO {

  @NotBlank(message = "o campo artistId é obrigatorio")
  private String artistId;

  @NotBlank(message = "o campo userId é obrigatorio")
  private String userId;

  public AddFavoriteArtistDTO(String artistId, String userId) {
    this.artistId = artistId;
    this.userId = userId;
  }

  public String getArtistId() {
    return artistId;
  }

  public String getUserId() {
    return userId;
  }
}
