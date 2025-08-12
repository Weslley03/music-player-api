package com.weftecnologia.music_player_api.repository;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.weftecnologia.music_player_api.dto.AddLibraryDTO;
import com.weftecnologia.music_player_api.dto.ResponseAlbumDTO;
import com.weftecnologia.music_player_api.dto.ResponseArtistDTO;
import com.weftecnologia.music_player_api.dto.ResponseLibraryDTO;
import com.weftecnologia.music_player_api.entity.Album;
import com.weftecnologia.music_player_api.entity.Artist;
import com.weftecnologia.music_player_api.entity.Library;
import com.weftecnologia.music_player_api.enums.LibraryType;
import com.weftecnologia.music_player_api.util.ConvertBinary;
import com.weftecnologia.music_player_api.util.GenerateUUID;

@Service
public class LibraryRepository {

  private MongoTemplate mongoTemplate;

  public LibraryRepository(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public Library addLibrary(AddLibraryDTO dto) {
    try {
      Date now = new Date();

      Library library = new Library(
          GenerateUUID.generate(),
          dto.getUserId(),
          dto.getRefId(),
          dto.getType(),
          now);

      mongoTemplate.insert(library, "library");

      return library;
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("erro ao adicionar biblioteca", e);
    }
  }

  private <T, D> List<D> findAndMap(
      List<Library> libraries,
      LibraryType filterBy,
      Class<T> entityClass,
      Function<T, D> mapper) {
    List<String> ids = libraries.stream()
        .filter(lib -> lib.getType() == filterBy)
        .map(Library::getRefId)
        .toList();

    if (ids.isEmpty()) {
      return List.of();
    }

    List<T> entities = mongoTemplate.find(
        Query.query(Criteria.where("id").in(ids)),
        entityClass);

    return entities.stream()
        .map(mapper)
        .toList();
  }

  public ResponseLibraryDTO findAllByUserId(String userId) {
    try {
      Query query = new Query();
      query.addCriteria(Criteria.where("userId").is(userId));
      List<Library> libraries = mongoTemplate.find(query, Library.class);

      List<ResponseAlbumDTO> albumDTOs = findAndMap(
          libraries,
          LibraryType.ALBUM,
          Album.class,
          album -> new ResponseAlbumDTO(
              album.getId(),
              ConvertBinary.toBase64(album.getImg()),
              album.getTitle(),
              album.getAuthor(),
              album.getCreatedAt()));

      List<ResponseArtistDTO> artistDTOs = findAndMap(
          libraries,
          LibraryType.ARTIST,
          Artist.class,
          artist -> new ResponseArtistDTO(
              artist.getId(),
              ConvertBinary.toBase64(artist.getImg()),
              artist.getName(),
              artist.getCreatedAt()));

      return new ResponseLibraryDTO(userId, albumDTOs, artistDTOs);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("erro ao buscar bibliteca relacionada ao usuário.", e);
    }
  }
}
