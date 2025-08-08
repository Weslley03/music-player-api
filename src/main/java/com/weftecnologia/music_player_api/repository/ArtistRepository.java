package com.weftecnologia.music_player_api.repository;

import java.util.Base64;
import java.util.Date;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.weftecnologia.music_player_api.dto.CreateArtistDTO;
import com.weftecnologia.music_player_api.dto.ResponseArtistDTO;
import com.weftecnologia.music_player_api.entity.Artist;
import com.weftecnologia.music_player_api.exception.handler.exceptions.GenericNotFoundException;
import com.weftecnologia.music_player_api.util.ConvertBinary;
import com.weftecnologia.music_player_api.util.GenerateUUID;

@Service
public class ArtistRepository {

  private final MongoTemplate mongoTemplate;

  public ArtistRepository(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  };

  public ResponseArtistDTO insert(CreateArtistDTO dto) {
    try {
      Binary imgDecoded = new Binary(Base64.getDecoder().decode(dto.getImg()));
      Date now = new Date();

      Artist artist = new Artist(
          GenerateUUID.generate(),
          imgDecoded,
          dto.getName(),
          now);

      mongoTemplate.insert(artist, "artist");

      String imgInBase64 = ConvertBinary.toBase64(artist.getImg());

      ResponseArtistDTO artistResponse = new ResponseArtistDTO(
          artist.getId(),
          imgInBase64,
          artist.getName(),
          artist.getCreatedAt());

      return artistResponse;
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("erro ao inserir artista.", e);
    }
  }

  public ResponseArtistDTO getArtistById(String artistId) {
    try {
      Artist artist = mongoTemplate.findById(artistId, Artist.class);

      if (artist == null) {
        throw new GenericNotFoundException("artista com ID " + artistId + " não encontrado.");
      }

      String imgInBase64 = ConvertBinary.toBase64(artist.getImg());

      ResponseArtistDTO artistResponse = new ResponseArtistDTO(
          artist.getId(),
          imgInBase64,
          artist.getName(),
          artist.getCreatedAt());

      return artistResponse;
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("erro ao buscar artista.", e);
    }
  }
}
