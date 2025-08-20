package com.weftecnologia.music_player_api.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

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

  public List<Library> addLibrary(AddLibraryDTO dto) {
    try {
      List<Library> existingLibraries = this.findLibrariesByUserId(dto.getUserId());

      Set<String> existingRefIds = existingLibraries.stream()
          .map(Library::getRefId)
          .collect(Collectors.toSet());

      List<Library> librariesToInsert = new ArrayList<>();

      LocalDate now = LocalDate.now();

      for (String refId : dto.getRefId()) {
        if (!existingRefIds.contains(refId)) {
          Library library = new Library(
              GenerateUUID.generate(),
              dto.getUserId(),
              refId,
              dto.getType(),
              now);
          librariesToInsert.add(library);
        }
      }

      if (!librariesToInsert.isEmpty()) {
        mongoTemplate.insert(librariesToInsert, "library");
      }

      return this.findLibrariesByUserId(dto.getUserId());
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("erro ao adicionar biblioteca", e);
    }
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

  private boolean verifyIfLiked(String userId, String refId) {
    return likeRepository.hasLike(userId, refId);
  }

  private List<Library> findLibrariesByUserId(String userId) {
    Query query = new Query();
    query.addCriteria(Criteria.where("userId").is(userId));
    return mongoTemplate.find(query, Library.class);
  }
}
