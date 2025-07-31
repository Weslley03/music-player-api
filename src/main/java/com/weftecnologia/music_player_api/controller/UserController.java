package com.weftecnologia.music_player_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weftecnologia.music_player_api.dto.CreateUserDTO;
import com.weftecnologia.music_player_api.dto.ResponseApiDTO;
import com.weftecnologia.music_player_api.dto.ResponseUserDTO;
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
    ResponseApiDTO<ResponseUserDTO> response = new ResponseApiDTO<ResponseUserDTO>(
        "usuário cadastrado com sucesso.",
        HttpStatus.OK.value(),
        userResponse);

    return response;
  }
}
