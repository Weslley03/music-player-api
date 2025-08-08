package com.weftecnologia.music_player_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weftecnologia.music_player_api.dto.CreateArtistDTO;
import com.weftecnologia.music_player_api.dto.ResponseApiDTO;
import com.weftecnologia.music_player_api.dto.ResponseArtistDTO;
import com.weftecnologia.music_player_api.repository.ArtistRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/artist")
public class ArtistController {

  private final ArtistRepository artistRepository;

  public ArtistController(ArtistRepository artistRepository) {
    this.artistRepository = artistRepository;
  };

  @PostMapping
  public ResponseApiDTO<ResponseArtistDTO> create(@Valid @RequestBody CreateArtistDTO dto) {
    ResponseArtistDTO albumResponse = artistRepository.insert(dto);
    return new ResponseApiDTO<ResponseArtistDTO>(
        "artista cadastrado com sucesso.",
        HttpStatus.OK.value(),
        albumResponse);
  }

  @GetMapping("/by/{albumId}")
  public ResponseApiDTO<ResponseArtistDTO> getAlbumById(@PathVariable String albumId) {
    ResponseArtistDTO albumResponse = artistRepository.getArtistById(albumId);
    return new ResponseApiDTO<ResponseArtistDTO>(
        "artista encontrado com sucesso.",
        HttpStatus.OK.value(),
        albumResponse);
  }
}
