package com.weftecnologia.music_player_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weftecnologia.music_player_api.dto.ResponseApiDTO;
import com.weftecnologia.music_player_api.dto.user.CreateUserDTO;
import com.weftecnologia.music_player_api.dto.user.ResponseUserDTO;
import com.weftecnologia.music_player_api.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  };

  @PostMapping
  public ResponseApiDTO<ResponseUserDTO> create(@Valid @RequestBody CreateUserDTO dto) {
    ResponseUserDTO userResponse = userRepository.insert(dto);
    return new ResponseApiDTO<ResponseUserDTO>(
        "usuário cadastrado com sucesso.",
        HttpStatus.OK.value(),
        userResponse);
  }

  @GetMapping("/informations/{id}")
  public ResponseApiDTO<ResponseUserDTO> getUserInformations(@PathVariable String id) {
    ResponseUserDTO userResponse = userRepository.getInformationsById(id);
    return new ResponseApiDTO<ResponseUserDTO>(
        "usuário encontrado com sucesso.",
        HttpStatus.OK.value(),
        userResponse);
  }
}
