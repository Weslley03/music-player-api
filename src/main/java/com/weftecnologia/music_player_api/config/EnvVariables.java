package com.weftecnologia.music_player_api.config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvVariables {

  private static final Dotenv dotenv = Dotenv.load();

  public static String getMongoUri() {
    return dotenv.get("MONGODB_URI");
  }
}
