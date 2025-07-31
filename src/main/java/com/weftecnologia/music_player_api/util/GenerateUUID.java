package com.weftecnologia.music_player_api.util;

import java.util.UUID;

public class GenerateUUID {
  public static String generate() {
    return UUID.randomUUID().toString();
  }
}
