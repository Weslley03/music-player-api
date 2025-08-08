package com.weftecnologia.music_player_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weftecnologia.music_player_api.dto.CreateAlbumDTO;
import com.weftecnologia.music_player_api.dto.ResponseAlbumDTO;
import com.weftecnologia.music_player_api.dto.ResponseApiDTO;
import com.weftecnologia.music_player_api.repository.AlbumRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/album")
public class AlbumController {

  private final AlbumRepository albumRepository;

  public AlbumController(AlbumRepository albumRepository) {
    this.albumRepository = albumRepository;
  };

  @PostMapping
  public ResponseApiDTO<ResponseAlbumDTO> create(@Valid @RequestBody CreateAlbumDTO dto) {
    ResponseAlbumDTO albumResponse = albumRepository.insert(dto);
    return new ResponseApiDTO<ResponseAlbumDTO>(
        "álbum cadastrado com sucesso.",
        HttpStatus.OK.value(),
        albumResponse);
  }

  @GetMapping("/by/{albumId}")
  public ResponseApiDTO<ResponseAlbumDTO> getAlbumById(@PathVariable String albumId) {
    ResponseAlbumDTO albumResponse = albumRepository.getAlbumById(albumId);
    return new ResponseApiDTO<ResponseAlbumDTO>(
        "álbum encontrado com sucesso.",
        HttpStatus.OK.value(),
        albumResponse);
  }
}
