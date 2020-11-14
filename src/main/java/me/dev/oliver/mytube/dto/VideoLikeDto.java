package me.dev.oliver.mytube.dto;

import lombok.Getter;

@Getter
public class VideoLikeDto {

  private int videoId;

  private String userId;

  private LikeType likeType;

  public enum LikeType {
    DISLIKE, LIKE
  }

}

