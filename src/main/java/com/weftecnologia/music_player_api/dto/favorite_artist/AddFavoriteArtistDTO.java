package com.weftecnologia.music_player_api.dto.favorite_artist;

public class AddFavoriteArtistDTO {
  private String artistId;
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
