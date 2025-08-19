package com.weftecnologia.music_player_api.dto.user;

public class ResponseAuthDTO {
  private boolean success;
  private String message;
  private ResponseUserDTO user;

  public ResponseAuthDTO(boolean success, String message) {
    this.success = success;
    this.message = message;
  }

  public ResponseAuthDTO(boolean success, String message, ResponseUserDTO user) {
    this.success = success;
    this.message = message;
    this.user = user;
  }

  public boolean isSuccess() {
    return success;
  }

  public String getMessage() {
    return message;
  }

  public ResponseUserDTO getUser() {
    return user;
  }
}
