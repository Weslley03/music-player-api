package com.weftecnologia.music_player_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weftecnologia.music_player_api.dto.CreateMusicDTO;
import com.weftecnologia.music_player_api.dto.ResponseApiDTO;
import com.weftecnologia.music_player_api.dto.ResponseMusicDTO;
import com.weftecnologia.music_player_api.repository.MusicRepository;

@RestController
@RequestMapping("/music")
public class MusicController {

  private final MusicRepository musicRepository;

  public MusicController(MusicRepository musicRepository) {
    this.musicRepository = musicRepository;
  }

  @PostMapping
  public ResponseApiDTO<ResponseMusicDTO> insert(@RequestBody CreateMusicDTO dto) {
    ResponseMusicDTO responseMusicDTO = musicRepository.insert(dto);
    return new ResponseApiDTO<ResponseMusicDTO>(
        "música cadastrada com sucesso",
        HttpStatus.OK.value(),
        responseMusicDTO);
  }

  @GetMapping("/{type}/{refId}")
  public ResponseApiDTO<List<ResponseMusicDTO>> getByRefId(
      @PathVariable String type,
      @PathVariable String refId) {
    List<ResponseMusicDTO> responseMusicDTOs = musicRepository.getByRefId(type, refId);
    return new ResponseApiDTO<List<ResponseMusicDTO>>(
        "músicas encontradas:",
        HttpStatus.OK.value(),
        responseMusicDTOs);
  }
}
