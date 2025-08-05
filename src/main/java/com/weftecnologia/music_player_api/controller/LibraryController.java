package com.weftecnologia.music_player_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weftecnologia.music_player_api.dto.AddLibraryDTO;
import com.weftecnologia.music_player_api.dto.ResponseApiDTO;
import com.weftecnologia.music_player_api.dto.ResponseLibraryDTO;
import com.weftecnologia.music_player_api.repository.LibraryRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/library")
public class LibraryController {

  private final LibraryRepository libraryRepository;

  public LibraryController(LibraryRepository libraryRepository) {
    this.libraryRepository = libraryRepository;
  }

  @PostMapping
  public ResponseApiDTO<ResponseLibraryDTO> addLibrary(@Valid @RequestBody AddLibraryDTO dto) {
    ResponseLibraryDTO library = libraryRepository.addLibrary(dto);
    return new ResponseApiDTO<ResponseLibraryDTO>(
        "biblioteca cadastrada com sucesso.",
        HttpStatus.OK.value(),
        library);
  };

  @GetMapping("/by/{userId}")
  public ResponseApiDTO<List<ResponseLibraryDTO>> findAllByUserId(@PathVariable String userId) {
    List<ResponseLibraryDTO> libraryList = libraryRepository.findAllByUserId(userId);
    return new ResponseApiDTO<List<ResponseLibraryDTO>>(
        "bibliotecas relacionadas ao usuário encontradas",
        HttpStatus.OK.value(),
        libraryList);
  }
}
