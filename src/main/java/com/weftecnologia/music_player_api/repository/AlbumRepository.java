package com.weftecnologia.music_player_api.repository;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.bson.types.Binary;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
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

  public List<ResponseAlbumDTO> getAllAlbums() {
    try {
      Query query = new Query();
      query.limit(10);
      List<Album> albums = mongoTemplate.find(query, Album.class);

      return albums.stream()
          .map(
              album -> new ResponseAlbumDTO(
                  album.getId(),
                  ConvertBinary.toBase64(album.getImg()),
                  album.getTitle(),
                  album.getAuthor(),
                  album.getCreatedAt(),
                  false))
          .toList();
    } catch (Exception e) {
      e.printStackTrace();
      throw new DataAccessResourceFailureException("erro ao tentar buscar albums", e);
    }
  }
}
