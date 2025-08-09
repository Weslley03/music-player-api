package com.weftecnologia.music_player_api.dto;

import com.weftecnologia.music_player_api.enums.LibraryType;

import jakarta.validation.constraints.NotBlank;

public class AddLibraryDTO {

  @NotBlank(message = "userId é obrigatório")
  private String userId;

  @NotBlank(message = "refId é obrigatório")
  private String refId;

  @NotBlank(message = "type é obrigatório")
  private LibraryType type;

  public AddLibraryDTO(String userId, String refId, LibraryType type) {
    this.userId = userId;
    this.refId = refId;
    this.type = type;
  }

  public String getUserId() {
    return userId;
  }

  public String getRefId() {
    return refId;
  }

  public LibraryType getType() {
    return type;
  }
}
