package com.weftecnologia.music_player_api.repository;

import java.util.Base64;
import java.util.Date;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.weftecnologia.music_player_api.dto.album.CreateAlbumDTO;
import com.weftecnologia.music_player_api.dto.album.ResponseAlbumDTO;
import com.weftecnologia.music_player_api.entity.Album;
import com.weftecnologia.music_player_api.exception.handler.exceptions.GenericNotFoundException;
import com.weftecnologia.music_player_api.util.ConvertBinary;
import com.weftecnologia.music_player_api.util.GenerateUUID;

@Service
public class AlbumRepository {

  private final MongoTemplate mongoTemplate;

  public AlbumRepository(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  };

  public ResponseAlbumDTO insert(CreateAlbumDTO dto) {
    try {
      Binary imgDecoded = new Binary(Base64.getDecoder().decode(dto.getImg()));
      Date now = new Date();

      Album album = new Album(
          GenerateUUID.generate(),
          imgDecoded,
          dto.getTitle(),
          dto.getAuthor(),
          now);

      mongoTemplate.insert(album, "album");

      String imgInBase64 = ConvertBinary.toBase64(album.getImg());

      ResponseAlbumDTO albumResponse = new ResponseAlbumDTO(
          album.getId(),
          imgInBase64,
          album.getTitle(),
          album.getAuthor(),
          album.getCreatedAt(),
          false);

      return albumResponse;
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("erro ao inserir álbum.", e);
    }
  }

  public ResponseAlbumDTO getAlbumById(String albumId) {
    try {
      Album album = mongoTemplate.findById(albumId, Album.class);

      if (album == null) {
        throw new GenericNotFoundException("álbum com ID " + albumId + " não encontrado.");
      }

      String imgInBase64 = ConvertBinary.toBase64(album.getImg());

      ResponseAlbumDTO albumResponse = new ResponseAlbumDTO(
          album.getId(),
          imgInBase64,
          album.getTitle(),
          album.getAuthor(),
          album.getCreatedAt(),
          false);

      return albumResponse;
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("erro ao buscar álbum.", e);
    }
  }
}
