package com.weftecnologia.music_player_api.dto;

public class ResponseApiDTO<T> {
  private String message;
  private int status;
  private T response;

  public ResponseApiDTO(String message, int status, T response) {
    this.message = message;
    this.status = status;
    this.response = response;
  }

  public String getMessage() {
    return message;
  }

  public int getStatus() {
    return status;
  }

  public T getResponse() {
    return response;
  }
}
