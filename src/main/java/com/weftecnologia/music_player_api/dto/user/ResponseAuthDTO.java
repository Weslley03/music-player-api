package com.weftecnologia.music_player_api.dto.user;

public class ResponseAuthDTO {
  private boolean success;
  private String message;
  private String token;
  private ResponseUserDTO user;

  public ResponseAuthDTO(boolean success, String message) {
    this.success = success;
    this.message = message;
  }

  public ResponseAuthDTO(boolean success, String message, String token, ResponseUserDTO user) {
    this.success = success;
    this.message = message;
    this.token = token;
    this.user = user;
  }

  public boolean isSuccess() {
    return success;
  }

  public String getMessage() {
    return message;
  }

  public String getToken() {
    return token;
  }

  public ResponseUserDTO getUser() {
    return user;
  }
}
