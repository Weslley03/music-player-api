package com.weftecnologia.music_player_api.dto.library;

import java.util.List;

import com.weftecnologia.music_player_api.enums.LibraryType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class AddLibraryDTO {

  @NotBlank(message = "userId é obrigatório")
  private String userId;

  @NotEmpty(message = "A lista não pode estar vazia")
  private List<@NotBlank(message = "refId é obrigatório") String> refId;

  private LibraryType type;

  public AddLibraryDTO(String userId, List<String> refId, LibraryType type) {
    this.userId = userId;
    this.refId = refId;
    this.type = type;
  }

  public String getUserId() {
    return userId;
  }

  public List<String> getRefId() {
    return refId;
  }

  public LibraryType getType() {
    return type;
  }
}
