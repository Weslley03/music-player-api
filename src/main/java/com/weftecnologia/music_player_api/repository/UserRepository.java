package com.weftecnologia.music_player_api.repository;

import java.util.Base64;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.weftecnologia.music_player_api.dto.CreateUserDTO;
import com.weftecnologia.music_player_api.dto.ResponseUserDTO;
import com.weftecnologia.music_player_api.entity.User;
import com.weftecnologia.music_player_api.exception.handler.exceptions.GenericNotFoundException;
import com.weftecnologia.music_player_api.util.ConvertBinary;
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

      String avatarInBase64 = ConvertBinary.toBase64(user.getAvatar());

      ResponseUserDTO userResponse = new ResponseUserDTO(
          user.getId(),
          user.getName(),
          user.getEmail(),
          avatarInBase64);

      return userResponse;
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("erro ao inserir usuário.", e);
    }
  }

  public ResponseUserDTO getInformationsById(String id) {
    try {
      User user = mongoTemplate.findById(id, User.class);

      if (user == null) {
        throw new GenericNotFoundException("usuário com ID " + id + " não encontrado.");
      }

      String avatarInBase64 = ConvertBinary.toBase64(user.getAvatar());

      ResponseUserDTO userResponse = new ResponseUserDTO(
          user.getId(),
          user.getName(),
          user.getEmail(),
          avatarInBase64);

      return userResponse;
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("erro ao buscar usuário.", e);
    }
  }
}