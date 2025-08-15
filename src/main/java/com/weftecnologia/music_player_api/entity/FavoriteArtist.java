package com.weftecnologia.music_player_api.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "favorite_artist")
public class FavoriteArtist {
  private String artistId;
  private String userId;

  public FavoriteArtist(String artistId, String userId) {
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
