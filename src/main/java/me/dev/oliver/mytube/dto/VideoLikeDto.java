package me.dev.oliver.mytube.dto;

import lombok.Getter;

@Getter
public class VideoLikeDto {

  private int videoId;

  private String userId;

  // Boolean 타입으로 해야 lombok Getter가 인식함
  private Boolean isLiked;

}