package com.weftecnologia.music_player_api.repository;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClient;
import com.weftecnologia.music_player_api.dto.favorite_artist.AddFavoriteArtistDTO;
import com.weftecnologia.music_player_api.dto.library.AddLibraryDTO;
import com.weftecnologia.music_player_api.entity.FavoriteArtist;
import com.weftecnologia.music_player_api.enums.LibraryType;

@Service
public class FavoriteArtistRepository {
  private final MongoTemplate mongoTemplate;
  private final MongoClient mongoClient;

  private final LibraryRepository libraryRepository;

  public FavoriteArtistRepository(
      MongoTemplate mongoTemplate,
      LibraryRepository libraryRepository,
      MongoClient mongoClient) {
    this.mongoTemplate = mongoTemplate;
    this.libraryRepository = libraryRepository;
    this.mongoClient = mongoClient;
  }

  public boolean add(AddFavoriteArtistDTO dto) {
    try (ClientSession session = mongoClient.startSession()) {
      session.startTransaction();
      try {
        FavoriteArtist favoriteArtist = new FavoriteArtist(dto.getArtistId(), dto.getUserId());
        mongoTemplate.insert(favoriteArtist, "favorite_artist");

        AddLibraryDTO libraryDTO = new AddLibraryDTO(dto.getArtistId(), dto.getUserId(), LibraryType.ARTIST);
        libraryRepository.addLibrary(libraryDTO);

        session.commitTransaction();
        return true;
      } catch (Exception e) {
        session.abortTransaction();
        e.printStackTrace();
        return false;
      }
    }
  }
}
