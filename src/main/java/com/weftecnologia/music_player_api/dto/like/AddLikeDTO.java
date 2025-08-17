package com.weftecnologia.music_player_api.dto.like;

import jakarta.validation.constraints.NotBlank;

public class AddLikeDTO {

  @NotBlank(message = "o campo userId é obrigatório")
  private String userId;

  @NotBlank(message = "o campo refId é obrigatório")
  private String refId;

  @NotBlank(message = "o campo refType é obrigatório")
  private String refType;

  public AddLikeDTO(String userId, String refId, String refType) {
    this.userId = userId;
    this.refId = refId;
    this.refType = refType;
  }

  public String getUserId() {
    return userId;
  }

  public String getRefId() {
    return refId;
  }

  public String getRefType() {
    return refType;
  }
}
