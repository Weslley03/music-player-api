package com.weftecnologia.music_player_api.repository;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.weftecnologia.music_player_api.dto.CreateMusicDTO;
import com.weftecnologia.music_player_api.dto.ResponseMusicDTO;
import com.weftecnologia.music_player_api.entity.Music;
import com.weftecnologia.music_player_api.util.ConvertBinary;
import com.weftecnologia.music_player_api.util.GenerateUUID;

@Service
public class MusicRepository {

  private final MongoTemplate mongoTemplate;

  public MusicRepository(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public ResponseMusicDTO insert(CreateMusicDTO dto) {
    try {
      Date now = new Date();
      Binary imgDecoded = new Binary(Base64.getDecoder().decode(dto.getImg()));
      Binary srcDecoded = new Binary(Base64.getDecoder().decode(dto.getSrc()));

      Music music = new Music(
          GenerateUUID.generate(),
          dto.getAlbumId(),
          dto.getArtistId(),
          imgDecoded,
          dto.getTitle(),
          dto.getAuthor(),
          srcDecoded,
          now);

      mongoTemplate.insert(music, "music");

      return new ResponseMusicDTO(
          music.getId(),
          music.getAlbumId(),
          music.getArtistId(),
          ConvertBinary.toBase64(music.getImg()),
          music.getTitle(),
          music.getAuthor(),
          ConvertBinary.toBase64(music.getSrc()),
          music.getCreatedAt());
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("erro ao inserir música.", e);
    }
  }

  public List<ResponseMusicDTO> getByRefId(String type, String refId) {
    try {
      Query query = new Query();

      if (type.equalsIgnoreCase("album")) {
        query.addCriteria(Criteria.where("albumId").is(refId));
      }

      else if (type.equalsIgnoreCase("artist")) {
        query.addCriteria(Criteria.where("artistId").is(refId));
      }

      List<Music> musics = mongoTemplate.find(query, Music.class);

      return musics.stream()
          .map(music -> new ResponseMusicDTO(
              music.getId(),
              music.getAlbumId(),
              music.getArtistId(),
              ConvertBinary.toBase64(music.getImg()),
              music.getTitle(),
              music.getAuthor(),
              ConvertBinary.toBase64(music.getSrc()),
              music.getCreatedAt()))
          .toList();
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("erro ao buscar musicas.", e);
    }
  }
}
