package com.weftecnologia.music_player_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weftecnologia.music_player_api.dto.ResponseApiDTO;
import com.weftecnologia.music_player_api.dto.favorite_artist.AddFavoriteArtistDTO;
import com.weftecnologia.music_player_api.repository.FavoriteArtistRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/favorite-artist")
public class FavoriteArtistController {
  private final FavoriteArtistRepository favoriteArtistRepository;

  public FavoriteArtistController(FavoriteArtistRepository favoriteArtistRepository) {
    this.favoriteArtistRepository = favoriteArtistRepository;
  }

  @PostMapping
  public ResponseApiDTO<Void> add(@Valid @RequestBody AddFavoriteArtistDTO dto) {
    boolean success = favoriteArtistRepository.add(dto);
    if (!success) {
      return new ResponseApiDTO<Void>("não foi possivel concluir a ação.", HttpStatus.BAD_REQUEST.value(), null);
    }
    return new ResponseApiDTO<Void>("adicionado com sucesso.", HttpStatus.OK.value(), null);
  }
}
