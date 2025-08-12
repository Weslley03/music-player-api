package com.weftecnologia.music_player_api.dto;

import java.util.List;

public class ResponseLibraryDTO {

  private String userId;
  private List<ResponseAlbumDTO> albums;
  private List<ResponseArtistDTO> artists;

  public ResponseLibraryDTO(String userId, List<ResponseAlbumDTO> albums, List<ResponseArtistDTO> artists) {
    this.userId = userId;
    this.albums = albums;
    this.artists = artists;
  }

  public String getUserId() {
    return userId;
  }

  public List<ResponseAlbumDTO> getAlbums() {
    return albums;
  }

  public List<ResponseArtistDTO> getArtists() {
    return artists;
  }
}
