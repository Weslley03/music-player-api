package com.weftecnologia.music_player_api.repository;

import java.util.Base64;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.weftecnologia.music_player_api.dto.CreateUserDTO;
import com.weftecnologia.music_player_api.dto.ResponseUserDTO;
import com.weftecnologia.music_player_api.entity.User;
import com.weftecnologia.music_player_api.util.GenerateUUID;

@Service
public class UserRepository {

  private final MongoTemplate mongoTemplate;

  public UserRepository(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  };

  public ResponseUserDTO insert(CreateUserDTO dto) {
    try {
      Binary avatarDecoded = new Binary(Base64.getDecoder().decode(dto.getAvatar()));

      User user = new User(
          GenerateUUID.generate(),
          dto.getName(),
          dto.getEmail(),
          dto.getPassword(),
          avatarDecoded);

      mongoTemplate.insert(user, "user");

      ResponseUserDTO userResponse = new ResponseUserDTO(user.getId(), user.getName(),
          user.getEmail(), user.getAvatar());

      return userResponse;
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("erro ao inserir usuário.", e);
    }
  }
}