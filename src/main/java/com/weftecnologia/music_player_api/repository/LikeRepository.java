package com.weftecnologia.music_player_api.repository;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.weftecnologia.music_player_api.dto.like.AddLikeDTO;
import com.weftecnologia.music_player_api.entity.Like;
import com.weftecnologia.music_player_api.util.GenerateUUID;

@Service
public class LikeRepository {

  private final MongoTemplate mongoTemplate;

  public LikeRepository(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public boolean insert(AddLikeDTO dto) {
    LocalDate now = LocalDate.now();

    try {
      Like like = new Like(
          GenerateUUID.generate(),
          dto.getUserId(),
          dto.getRefId(),
          dto.getRefType(),
          now);

      mongoTemplate.insert(like, "like");
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public boolean remove(String userId, String refId) {
    try {
      Query query = Query.query(Criteria.where("refId").is(refId).and("userId").is(userId));
      mongoTemplate.remove(query, Like.class);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
}
