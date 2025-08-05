package com.weftecnologia.music_player_api.repository;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.weftecnologia.music_player_api.dto.AddLibraryDTO;
import com.weftecnologia.music_player_api.dto.ResponseLibraryDTO;
import com.weftecnologia.music_player_api.entity.Library;
import com.weftecnologia.music_player_api.util.ConvertBinary;
import com.weftecnologia.music_player_api.util.GenerateUUID;

@Service
public class LibraryRepository {

  private MongoTemplate mongoTemplate;

  public LibraryRepository(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public ResponseLibraryDTO addLibrary(AddLibraryDTO dto) {
    try {
      Binary imgDecoded = new Binary(Base64.getDecoder().decode(dto.getImg()));
      Date now = new Date();

      Library library = new Library(
          GenerateUUID.generate(),
          dto.getUserId(),
          imgDecoded,
          dto.getTitle(),
          dto.getDescription(),
          now,
          dto.getType());

      mongoTemplate.insert(library, "library");

      return new ResponseLibraryDTO(
          library.getId(),
          library.getUserId(),
          ConvertBinary.toBase64(library.getImg()),
          library.getTitle(),
          library.getDescription(),
          library.getCreatedAt(),
          library.getType());
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("erro ao adicionar biblioteca", e);
    }
  }

  public List<ResponseLibraryDTO> findAllByUserId(String userId) {
    try {
      Query query = new Query();
      query.addCriteria(Criteria.where("userId").is(userId));
      List<Library> libraries = mongoTemplate.find(query, Library.class);

      return libraries.stream()
          .map(library -> new ResponseLibraryDTO(
              library.getId(),
              library.getUserId(),
              ConvertBinary.toBase64(library.getImg()),
              library.getTitle(),
              library.getDescription(),
              library.getCreatedAt(),
              library.getType()))
          .collect(Collectors.toList());
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("erro ao buscar bibliteca relacionada ao usuário.", e);
    }
  }
}
