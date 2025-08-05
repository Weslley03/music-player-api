package com.weftecnologia.music_player_api.util;

import java.util.Base64;

import org.bson.types.Binary;

public class ConvertBinary {
  public static String toBase64(Binary binary) {
    byte[] bytes = binary.getData();
    return Base64.getEncoder().encodeToString(bytes);
  }
}
