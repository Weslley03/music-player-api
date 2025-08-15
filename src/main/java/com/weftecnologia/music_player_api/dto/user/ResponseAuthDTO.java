package com.weftecnologia.music_player_api.dto.user;

public class ResponseAuthDTO {
  private boolean success;
  private String message;

  public ResponseAuthDTO(boolean success, String message) {
    this.success = success;
    this.message = message;
  }

  public boolean isSuccess() {
    return success;
  }

  public String getMessage() {
    return message;
  }
}
