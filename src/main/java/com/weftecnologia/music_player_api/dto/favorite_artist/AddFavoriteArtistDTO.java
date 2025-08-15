package com.weftecnologia.music_player_api.dto.favorite_artist;

import com.weftecnologia.music_player_api.enums.LibraryType;

import jakarta.validation.constraints.NotBlank;

public class AddFavoriteArtistDTO {

  @NotBlank(message = "o campo artistId é obrigatorio")
  private String artistId;

  @NotBlank(message = "o campo userId é obrigatorio")
  private String userId;

  @NotBlank(message = "o campo type é obrigatorio")
  private LibraryType type;

  public AddFavoriteArtistDTO(String artistId, String userId, LibraryType type) {
    this.artistId = artistId;
    this.userId = userId;
    this.type = type;
  }

  public String getArtistId() {
    return artistId;
  }

  public String getUserId() {
    return userId;
  }

  public LibraryType getType() {
    return type;
  }
}
