package com.weftecnologia.music_player_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.weftecnologia.music_player_api.security.BCryptPasswordEncoderImpl;
import com.weftecnologia.music_player_api.security.PasswordEncoder;

@Configuration
public class SecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoderImpl();
  }
}
