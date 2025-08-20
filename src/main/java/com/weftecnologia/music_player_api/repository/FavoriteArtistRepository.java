package com.weftecnologia.music_player_api.repository;

import java.util.ArrayList;
import java.util.List;

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

        List<FavoriteArtist> favoriteArtistToInsert = new ArrayList<>();

        for (String artistId : dto.getArtistId()) {
          FavoriteArtist favoriteArtist = new FavoriteArtist(artistId, dto.getUserId());
          favoriteArtistToInsert.add(favoriteArtist);
        }

        mongoTemplate.insert(favoriteArtistToInsert, "favorite_artist");

        AddLibraryDTO libraryDTO = new AddLibraryDTO(dto.getUserId(), dto.getArtistId(), LibraryType.ARTIST);
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
