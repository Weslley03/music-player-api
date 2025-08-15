package com.weftecnologia.music_player_api.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderImpl implements PasswordEncoder {

  private final BCryptPasswordEncoder encoder;

  public BCryptPasswordEncoderImpl() {
    this.encoder = new BCryptPasswordEncoder(12);
  }

  @Override
  public String encode(String rawPassword) {
    return encoder.encode(rawPassword);
  }

  @Override
  public boolean matches(String rawPassword, String encodedPassword) {
    return encoder.matches(rawPassword, encodedPassword);
  }
}
