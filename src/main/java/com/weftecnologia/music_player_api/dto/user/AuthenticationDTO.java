package com.weftecnologia.music_player_api.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AuthenticationDTO {

  @NotBlank(message = "email para autenticação é obrigaorio")
  @Email(message = "formato de email inválido")
  private String email;

  @NotBlank(message = "password para autenticação é obrigaorio")
  private String password;

  public AuthenticationDTO(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }
}