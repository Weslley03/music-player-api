package com.weftecnologia.music_player_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CreateUserDTO {

  @NotBlank(message = "o campo nome é obrigatório")
  private String name;

  @NotBlank(message = "o campo email é obrigatório")
  @Email(message = "formato de email inválido")
  private String email;

  @NotBlank(message = "o campo password é obrigatório")
  private String password;

  @NotBlank(message = "o campo avatar é obrigatório")
  private String avatar;

  public CreateUserDTO(String name, String email, String password, String avatar) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.avatar = avatar;
  };

  public String getName() {
    return this.name;
  };

  public String getEmail() {
    return this.email;
  };

  public String getPassword() {
    return this.password;
  };

  public String getAvatar() {
    return this.avatar;
  }
}
