package com.weftecnologia.music_player_api.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.weftecnologia.music_player_api.dto.album.ResponseAlbumDTO;
import com.weftecnologia.music_player_api.dto.artist.ResponseArtistDTO;
import com.weftecnologia.music_player_api.dto.library.AddLibraryDTO;
import com.weftecnologia.music_player_api.dto.library.ResponseLibraryDTO;
import com.weftecnologia.music_player_api.entity.Album;
import com.weftecnologia.music_player_api.entity.Artist;
import com.weftecnologia.music_player_api.entity.Library;
import com.weftecnologia.music_player_api.enums.LibraryType;
import com.weftecnologia.music_player_api.util.ConvertBinary;
import com.weftecnologia.music_player_api.util.GenerateUUID;

@Service
public class LibraryRepository {

  private MongoTemplate mongoTemplate;
  private LikeRepository likeRepository;

  public LibraryRepository(MongoTemplate mongoTemplate, LikeRepository likeRepository) {
    this.mongoTemplate = mongoTemplate;
    this.likeRepository = likeRepository;
  }

  public Library addLibrary(AddLibraryDTO dto) {
    try {
      Library existingLibrary = this.verifyIfExist(dto.getUserId(), dto.getRefId());

      if (existingLibrary != null) {
        return existingLibrary;
      }

      LocalDate now = LocalDate.now();

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

  private boolean verifyIfLiked(String userId, String refId) {
    return likeRepository.hasLike(userId, refId);
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
              album.getCreatedAt(),
              verifyIfLiked(userId, album.getId())));

      List<ResponseArtistDTO> artistDTOs = findAndMap(
          libraries,
          LibraryType.ARTIST,
          Artist.class,
          artist -> new ResponseArtistDTO(
              artist.getId(),
              ConvertBinary.toBase64(artist.getImg()),
              artist.getName(),
              artist.getCreatedAt(),
              verifyIfLiked(userId, artist.getId())));

      return new ResponseLibraryDTO(userId, albumDTOs, artistDTOs);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("erro ao buscar bibliteca relacionada ao usuário.", e);
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

  private Library verifyIfExist(String userId, String refId) {
    Query query = new Query();
    query.addCriteria(Criteria.where("userId").is(userId).and("refId").is(refId));
    return mongoTemplate.findOne(query, Library.class);
  };
}
