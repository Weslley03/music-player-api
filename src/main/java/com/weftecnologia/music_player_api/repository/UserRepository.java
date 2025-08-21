package com.weftecnologia.music_player_api.repository;

import java.util.Base64;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.weftecnologia.music_player_api.dto.user.AuthenticationDTO;
import com.weftecnologia.music_player_api.dto.user.CreateUserDTO;
import com.weftecnologia.music_player_api.dto.user.ResponseAuthDTO;
import com.weftecnologia.music_player_api.dto.user.ResponseUserDTO;
import com.weftecnologia.music_player_api.entity.User;
import com.weftecnologia.music_player_api.exception.handler.exceptions.GenericNotFoundException;
import com.weftecnologia.music_player_api.security.PasswordEncoder;
import com.weftecnologia.music_player_api.util.ConvertBinary;
import com.weftecnologia.music_player_api.util.GenerateUUID;

@Service
public class UserRepository {

  private final MongoTemplate mongoTemplate;
  private final PasswordEncoder encoder;

  public UserRepository(MongoTemplate mongoTemplate, PasswordEncoder encoder) {
    this.mongoTemplate = mongoTemplate;
    this.encoder = encoder;
  };

  public ResponseUserDTO insert(CreateUserDTO dto) {
    try {
      Binary avatarDecoded = new Binary(Base64.getDecoder().decode(dto.getAvatar()));

      User user = new User(
          GenerateUUID.generate(),
          dto.getName(),
          dto.getEmail(),
          encoder.encode(dto.getPassword()),
          avatarDecoded,
          true);

      mongoTemplate.insert(user, "user");

      String avatarInBase64 = ConvertBinary.toBase64(user.getAvatar());

      ResponseUserDTO userResponse = new ResponseUserDTO(
          user.getId(),
          user.getName(),
          user.getEmail(),
          avatarInBase64,
          user.isFirstAccess());

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
          avatarInBase64,
          user.isFirstAccess());

      return userResponse;
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("erro ao buscar usuário.", e);
    }
  }

  public ResponseAuthDTO login(AuthenticationDTO dto) {
    Query emailQuery = Query.query(Criteria.where("email").is(dto.getEmail()));
    User user = mongoTemplate.findOne(emailQuery, User.class);

    if (user == null) {
      return new ResponseAuthDTO(false, "usuário com email '" + dto.getEmail() + "' não encontrado.");
    }

    if (!encoder.matches(dto.getPassword(), user.getPassword())) {
      return new ResponseAuthDTO(false, "senha pro email '" + dto.getEmail() + "'' inválida.");
    }

    ResponseUserDTO responseUserDTO = new ResponseUserDTO(
        user.getId(),
        user.getName(),
        user.getEmail(),
        ConvertBinary.toBase64(user.getAvatar()),
        user.isFirstAccess());

    Update updateFirstAccess = new Update().set("firstAccess", false);
    mongoTemplate.updateFirst(emailQuery, updateFirstAccess, User.class);

    return new ResponseAuthDTO(true, "login realizado com sucesso.", "0123456789", responseUserDTO);
  }
}