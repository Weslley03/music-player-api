package com.weftecnologia.music_player_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weftecnologia.music_player_api.dto.ResponseApiDTO;
import com.weftecnologia.music_player_api.dto.like.AddLikeDTO;
import com.weftecnologia.music_player_api.repository.LikeRepository;

@RestController
@RequestMapping
public class LikeController {

  private final LikeRepository likeRepository;

  public LikeController(LikeRepository likeRepository) {
    this.likeRepository = likeRepository;
  }

  @PostMapping("/like")
  public ResponseApiDTO<Void> addLike(@RequestBody AddLikeDTO dto) {
    boolean success = likeRepository.insert(dto);
    if (!success) {
      return new ResponseApiDTO<>("erro occour", HttpStatus.BAD_REQUEST.value());
    }
    return new ResponseApiDTO<>("LIKE ADDED!", HttpStatus.OK.value());
  }

  @DeleteMapping("/unlike/{userId}/{refId}")
  public ResponseApiDTO<Void> remove(@PathVariable String userId, @PathVariable String refId) {
    boolean success = likeRepository.remove(userId, refId);
    if (!success) {
      return new ResponseApiDTO<>("erro occour", HttpStatus.BAD_REQUEST.value());
    }
    return new ResponseApiDTO<>("SUCCESS UNKLIKE!", HttpStatus.OK.value());
  }
}
